package com.example.simplemovies.repositories

import androidx.lifecycle.LiveData
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.MovieDb
import com.example.simplemovies.database.asDomainObject
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.Result
import com.example.simplemovies.domain.asDatabaseObject
import com.example.simplemovies.network.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieRepository(private val movieDao: MovieDao) {

    private var viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val API_KEY = "eebddf3c28edf2691214c6ece5688e32"

    fun getMovies(): List<MovieDb> {
        syncData()
        return movieDao.getAll()
    }

    suspend fun getMovieDetails(movieId: Int): MovieResult {
        return TmdbApi.retrofitService.getMovieDetails(movieId, API_KEY)
    }

    suspend fun getMovieCast(movieId: Int): Cast {
        return TmdbApi.retrofitService.getMovieCredits(movieId, API_KEY)
    }

    private fun syncData() {
        scope.launch {
            TmdbApi.retrofitService.getPopularMovies(API_KEY).results.asDatabaseObject()
                .forEach { movie ->
                    movieDao.insert(movie)
                }
        }
    }

}