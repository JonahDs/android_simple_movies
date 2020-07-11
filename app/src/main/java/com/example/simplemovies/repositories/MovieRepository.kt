package com.example.simplemovies.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.MovieDb
import com.example.simplemovies.database.asDomainObject
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMovies
import com.example.simplemovies.domain.asDatabaseObject
import com.example.simplemovies.network.NetworkBounding
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.TmdbApi
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MovieRepository(private val movieDao: MovieDao) {

    private var repositoryJob = Job()

    private val scope = CoroutineScope(repositoryJob + Main)

    //Reload local movies with API results after 30 seconds
    private val dataManager = DataManager(30, TimeUnit.SECONDS)

    private val API_KEY = "eebddf3c28edf2691214c6ece5688e32"


    fun getMovies(): LiveData<Resource<PopularMovies>> {
        return object : NetworkBounding<PopularMovies, PopularMovies>() {
            override fun saveApiResToDb(item: PopularMovies) {
                scope.launch {
                    withContext(IO) {
                        movieDao.insert(item.results.asDatabaseObject())
                    }
                }
            }

            override fun shouldFetch(data: PopularMovies?): Boolean {
                return data == null || data.results.isEmpty() || data.results == null || dataManager.shouldRefresh("MAIN")
            }

            override fun fetchFromDb(): LiveData<PopularMovies> {
                val test = MediatorLiveData<PopularMovies>()
                scope.launch {
                    withContext(Main) {
                        test.addSource(movieDao.getAll()) {
                            test.removeSource(movieDao.getAll())
                            test.value = PopularMovies(it.asDomainObject())
                        }
                    }
                }
                return test
            }

            override fun makeApiCall(): LiveData<PopularMovies> {
                val test = MutableLiveData<PopularMovies>()
                scope.launch {
                    try {
                        test.value = TmdbApi.retrofitService.getPopularMovies(API_KEY)
                    } catch (e: Exception) {
                        test.value = null
                    }
                }
                return test
            }
        }.asLiveData()
    }


    suspend fun getMovieDetails(movieId: Int): MovieResult {
        return TmdbApi.retrofitService.getMovieDetails(movieId, API_KEY)
    }

    suspend fun getMovieCast(movieId: Int): Cast {
        return TmdbApi.retrofitService.getMovieCredits(movieId, API_KEY)
    }
}