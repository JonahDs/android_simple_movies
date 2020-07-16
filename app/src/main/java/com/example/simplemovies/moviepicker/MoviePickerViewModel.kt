package com.example.simplemovies.moviepicker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePickerViewModel @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

    private val viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _randomMovie = MutableLiveData<MovieNetwork>()

    val randomMovie: LiveData<MovieNetwork> get() = _randomMovie

    private var _navigationProperty = MutableLiveData<Int>()

    val navigationProperty get() = _navigationProperty

    init {
        fetchRandomMovie()
    }

    private fun fetchRandomMovie() {
        scope.launch {
            try {
                _randomMovie.value =  movieRepo.getRandomMovie().results.random()
            } catch (e: Exception) {
                Log.i("GENRES_EX_", e.message.toString())

            }
        }
    }

    fun clicked(movieId: Int) {
        Log.i("CLICKED", movieId.toString())
        _navigationProperty.value = movieId
    }

    fun navigationCompleted() {
        _navigationProperty.value = null
    }

}