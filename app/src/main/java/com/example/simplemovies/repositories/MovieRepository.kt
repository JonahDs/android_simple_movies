package com.example.simplemovies.repositories


import androidx.lifecycle.LiveData
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asMovieNetwork
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.asMovieDatabase
import com.example.simplemovies.network.NetworkBoundingNew
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val tmdbApi: TmdbApiService,
    private val movieDao: MovieDao
) {

    private var repositoryJob = Job()

    private val scope = CoroutineScope(repositoryJob + Main)

    //Reload local movies with API results after 30 seconds
    private val dataManager = DataManager(30, TimeUnit.SECONDS)


    /**
        return a Flow since Room call returns a flow
        networkbounding will trigger each time our database gets updated
     */
    suspend fun getMoviesOfFlow(): LiveData<Resource<MoviesWrapper>> {
        return object : NetworkBoundingNew<MoviesWrapper>() {
            override suspend fun saveApiResToDb(item: MoviesWrapper) = withContext(IO) {
                movieDao.insert(item.results.asMovieDatabase())
            }

            override fun shouldFetch(data: MoviesWrapper?) = data == null || data.results.isEmpty()

            override fun fetchFromDb(): Flow<MoviesWrapper> =
                movieDao.getAllFlowDistinc().map { MoviesWrapper(it.asMovieNetwork()) }

            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getPopularMovies()
            }
    }.asLiveData()
    }

    suspend fun getMovieDetails(type: String, id: Int): MovieResult = withContext(IO) {
        tmdbApi.getMovieDetails(type, id)
    }

    suspend fun getMovieCast(type: String, id: Int): Cast = withContext(IO) {
        tmdbApi.getMovieCredits(type, id)
    }

    suspend fun getRandomMovie() = withContext(IO) {
        tmdbApi.getRandomMovies()
    }

    suspend fun getMoviesOfQuery(query: String) = withContext(IO) {
        tmdbApi.getMoviesOfQuery(query)
    }

    suspend fun getDiscover(
        type: String? = "movie",
        genresInc: List<String>,
        genresExl: List<String>,
        score: Int = 0
    ) = withContext(IO) {
        tmdbApi.getDiscover(
            type,
            genresInc,
            genresExl,
            score,
            if (score == 0) null else score + 1
        )
    }
}