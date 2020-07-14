package com.example.simplemovies.detailscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailscreenViewModel @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

    private var viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _result = MutableLiveData<MovieResult>()

    val result: LiveData<MovieResult> get() = _result

    private val _movieCast = MutableLiveData<Cast>()

    val movieCast: LiveData<Cast> get() = _movieCast

    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected


    init {
//        getMovieDetails(movieId)
    }



    fun getMovieDetails(movieId: Int) {
        scope.launch {
            try {
                _result.value = movieRepo.getMovieDetails(movieId)
                _movieCast.value = movieRepo.getMovieCast(movieId)
            } catch (e: Exception) {
                Log.i("API_ERROR", e.message)
            }
        }
    }

    fun displayCastDetails(castId: Int) {
        _navSelected.value = castId
    }

    fun displayCastDetailsCompleted() {
        _navSelected.value = null
    }
}