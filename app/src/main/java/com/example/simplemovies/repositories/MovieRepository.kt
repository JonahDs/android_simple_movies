package com.example.simplemovies.repositories


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asMovieNetwork
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.asMovieDatabase
import com.example.simplemovies.network.NetworkBounding
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    fun getMovies(): LiveData<Resource<MoviesWrapper>> {
        return object : NetworkBounding<MoviesWrapper, MoviesWrapper>() {
            override fun saveApiResToDb(item: MoviesWrapper) {
                scope.launch {
                    withContext(IO) {
                        movieDao.insert(item.results.asMovieDatabase())
                    }
                }
            }

            override fun shouldFetch(data: MoviesWrapper?): Boolean {
                return data == null || data.results.isEmpty() || data.results == null || dataManager.shouldRefresh(
                    "MAIN"
                )
            }

            override fun fetchFromDb(): LiveData<MoviesWrapper> {
                val test = MediatorLiveData<MoviesWrapper>()
                scope.launch {
                    withContext(Main) {
                        test.addSource(movieDao.getAll()) {
                            test.removeSource(movieDao.getAll())
                            test.value = MoviesWrapper(it.asMovieNetwork())
                        }
                    }
                }
                return test
            }

            override fun makeApiCall(): LiveData<MoviesWrapper> {
                val test = MutableLiveData<MoviesWrapper>()
                scope.launch {
                    try {
                        test.value = tmdbApi.getPopularMovies()
                    } catch (e: Exception) {
                        Log.i("API_STATE", e.message.toString())

                        test.value = null
                    }
                }
                return test
            }
        }.asLiveData()
    }

    suspend fun getMovieDetails(type: String, id: Int): MovieResult = withContext(IO) {
        tmdbApi.getMovieDetails(type, id)
    }

    suspend fun getMovieCast(type: String, id: Int): Cast = withContext(IO) {
        tmdbApi.getMovieCredits(type, id)
    }


    //No need for these methods to implement our networkbounding since it will always be fetched from remote


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