package com.example.simplemovies.repositories


import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asMovieNetwork
import com.example.simplemovies.domain.CastWrapper
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.asMovieDatabase
import com.example.simplemovies.network.NetworkBounding
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.SimpleBounding
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.Dispatchers.IO
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
     * Example repository method that implements NetworkBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @return Flow<Resource<MoviesWrapper>>
     * */
    fun getMoviesOfFlow(): Flow<Resource<MoviesWrapper>> {
        dataManager.declareTimeout(1, TimeUnit.MINUTES)
        return object : NetworkBounding<MoviesWrapper>() {
            override fun saveApiResToDb(item: MoviesWrapper) {
                movieDao.clearTable()
                movieDao.insert(item.results.asMovieDatabase())
            }

            override fun shouldFetch(data: MoviesWrapper?) =
                data == null || data.results.isEmpty() || dataManager.shouldRefresh("movies")

            override fun fetchFromDb(): Flow<MoviesWrapper?> =
                movieDao.getAllFlowDistinct().map {
                    if (it.isEmpty()) {
                        null
                    } else {
                        MoviesWrapper(it.asMovieNetwork())
                    }
                }

            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getPopularMovies()
            }
        }.asFlow().flowOn(IO)
    }

    /**
     * Example repository method that implements SimpleBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @param type movie or tv
     * @param id movie or tv id
     * @return Flow<Resource<MovieResult>>
     * */
    fun getMovieDetails(type: String, id: Int): Flow<Resource<MovieResult>> {
        return object : SimpleBounding<MovieResult>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMovieDetails(type, id)
            }
        }.asFlow().flowOn(IO)
    }

    /**
     * Example repository method that implements SimpleBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @param type movie or tv
     * @param id movie or tv id
     * @return Flow<Resource<CastWrapper>>
     * */
    fun getMovieCast(type: String, id: Int): Flow<Resource<CastWrapper>> {
        return object : SimpleBounding<CastWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMovieCredits(type, id)
            }
        }.asFlow().flowOn(IO)
    }

    /**
     * Example repository method that implements SimpleBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @return Flow<Resource<MoviesWrapper>>
     * */
    fun getRandomMovie(): Flow<Resource<MoviesWrapper>> {
        return object : SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getRandomMovies()
            }
        }.asFlow().flowOn(IO)
    }

    /**
     * Example repository method that implements SimpleBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @param query search query
     * @return Flow<Resource<MoviesWrapper>>
     * */
    fun getMoviesOfQuery(query: String): Flow<Resource<MoviesWrapper>> {
        return object : SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall() = withContext(IO) {
                tmdbApi.getMoviesOfQuery(query)
            }
        }.asFlow().flowOn(IO)
    }

    /**
     * Example repository method that implements SimpleBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @param type movie or tv DEFAULT movie
     * @param genresInc list of genres to be included
     * @param genresExl list of genres to be excluded
     * @param score minimal userscore DEFAULT 0
     * @return Flow<Resource<MoviesWrapper>>
     * */
    fun getDiscover(
        type: String? = "movie",
        genresInc: List<String>,
        genresExl: List<String>,
        score: Int = 0
    ): Flow<Resource<MoviesWrapper>> {
        return object : SimpleBounding<MoviesWrapper>() {
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