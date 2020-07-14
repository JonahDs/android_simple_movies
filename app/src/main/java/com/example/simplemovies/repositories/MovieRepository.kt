package com.example.simplemovies.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asDomainObject
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMoviesWrapper
import com.example.simplemovies.domain.asDatabaseObject
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

    private val API_KEY = "eebddf3c28edf2691214c6ece5688e32"


    fun getMovies(): LiveData<Resource<PopularMoviesWrapper>> {
        return object : NetworkBounding<PopularMoviesWrapper, PopularMoviesWrapper>() {
            override fun saveApiResToDb(item: PopularMoviesWrapper) {
                scope.launch {
                    withContext(IO) {
                        movieDao.insert(item.results.asDatabaseObject())
                    }
                }
            }

            override fun shouldFetch(data: PopularMoviesWrapper?): Boolean {
                return data == null || data.results.isEmpty() || data.results == null || dataManager.shouldRefresh(
                    "MAIN"
                )
            }

            override fun fetchFromDb(): LiveData<PopularMoviesWrapper> {
                val test = MediatorLiveData<PopularMoviesWrapper>()
                scope.launch {
                    withContext(Main) {
                        test.addSource(movieDao.getAll()) {
                            test.removeSource(movieDao.getAll())
                            test.value = PopularMoviesWrapper(it.asDomainObject())
                        }
                    }
                }
                return test
            }

            override fun makeApiCall(): LiveData<PopularMoviesWrapper> {
                val test = MutableLiveData<PopularMoviesWrapper>()
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


    //No need for these methods to implement our networkbounding since it will always be fetched from remote
    suspend fun getMovieDetails(movieId: Int): MovieResult {
        return tmdbApi.getMovieDetails(movieId)
    }

    suspend fun getMovieCast(movieId: Int): Cast {
        return tmdbApi.getMovieCredits(movieId)
    }
}