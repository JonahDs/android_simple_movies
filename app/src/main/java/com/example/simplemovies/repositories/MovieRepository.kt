package com.example.simplemovies.repositories


import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asMovieNetwork
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.asMovieDatabase
import com.example.simplemovies.network.NetworkBoundingNew
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.SimpleBounding
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val tmdbApi: TmdbApiService,
    private val movieDao: MovieDao,
    private val dataManager: DataManager
) {
    /**
    return a Flow since Room call returns a flow
    networkbounding will trigger each time our database gets updated
     */
    fun getMoviesOfFlow(): Flow<Resource<MoviesWrapper>> {
        dataManager.declareTimeout(1, TimeUnit.MINUTES)
        return object : NetworkBoundingNew<MoviesWrapper>() {
            override fun saveApiResToDb(item: MoviesWrapper)  {
                movieDao.insert(item.results.asMovieDatabase())
            }

            override fun shouldFetch(data: MoviesWrapper?) = data == null || data.results.isEmpty() || dataManager.shouldRefresh("movies")

            override fun fetchFromDb(): Flow<MoviesWrapper> =
                movieDao.getAllFlowDistinct().map { MoviesWrapper(it.asMovieNetwork()) }

            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getPopularMovies()
            }
        }.asFlow().flowOn(IO)
    }

    suspend fun getMovieDetails(type: String, id: Int): Flow<Resource<MovieResult>>  {
        return object: SimpleBounding<MovieResult>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMovieDetails(type, id)
            }
        }.asFlow().flowOn(IO)
    }

    suspend fun getMovieCast(type: String, id: Int): Flow<Resource<Cast>> {
        return object: SimpleBounding<Cast>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMovieCredits(type, id)
            }
        }.asFlow().flowOn(IO)
    }

    suspend fun getRandomMovie() : Flow<Resource<MoviesWrapper>> {
        return object: SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getRandomMovies()
            }
        }.asFlow().flowOn(IO)
    }

    suspend fun getMoviesOfQuery(query: String) : Flow<Resource<MoviesWrapper>> {
        return object: SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMoviesOfQuery(query)
            }
        }.asFlow().flowOn(IO)
    }

    suspend fun getDiscover(
        type: String? = "movie",
        genresInc: List<String>,
        genresExl: List<String>,
        score: Int = 0
    ): Flow<Resource<MoviesWrapper>> {
        return object: SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getDiscover(
                    type,
                    genresInc,
                    genresExl,
                    score,
                    if (score == 0) null else score + 1
                )
            }
        }.asFlow().flowOn(IO)
    }
}