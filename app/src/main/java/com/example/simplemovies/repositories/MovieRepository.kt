package com.example.simplemovies.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asDomainObject
import com.example.simplemovies.domain.*
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
                        movieDao.insert(item.results.asDatabaseObject())
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
                            test.value = MoviesWrapper(it.asDomainObject())
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


    //No need for these methods to implement our networkbounding since it will always be fetched from remote
    suspend fun getMovieDetails(movieId: Int): MovieResult {
        return tmdbApi.getMovieDetails(movieId)
    }

    suspend fun getMovieCast(movieId: Int): Cast {
        return tmdbApi.getMovieCredits(movieId)
    }

    suspend fun getAllMovieGenres(): GenresWrapper {
        return tmdbApi.getAllMovieGenres()
    }

    suspend fun getMoviesWithGenre(id: String): MoviesWrapper {
        return tmdbApi.getMoviesWithGenre(id)
    }

    suspend fun getRandomMovie(): MoviesWrapper {
        return tmdbApi.getRandomMovies()
    }

    suspend fun getMoviesOfQuery(query: String): MoviesWrapper {
        return tmdbApi.getMoviesOfQuery(query)
    }
}