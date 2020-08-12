package com.example.simplemovies.moviepicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePickerViewModel @Inject constructor(private val movieRepo: MovieRepository) :
    ViewModel() {

    private val _randomMovie = MutableLiveData<MovieNetwork>()

    val randomMovie: LiveData<MovieNetwork> get() = _randomMovie

    private var _navigationProperty: Int? = null

    val navigationProperty get() = _navigationProperty

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus


    /**
     * Only on creation of the viewmodel fetch the genres, this prevents a configuration change
     * to call the repository (or API)
     * */
    init {
        fetchRandomMovie()
    }

    /**
     * Subscribe to the repository call and catch it's values
     * */
    fun fetchRandomMovie() {
        viewModelScope.launch {
            movieRepo.getRandomMovie().collect {
                manageMovieResource(it)
            }
        }
    }

    /**
     * Set the API status and data only if not null
     * */
    private fun manageMovieResource(resource: Resource<MoviesWrapper>) {
        resource.data?.let {
            val randomMovie = it.results.random()
            _navigationProperty = randomMovie.id
            _randomMovie.value = randomMovie
        }
        resource.status?.let {
            _apiStatus.value = it
        }
    }

    fun navigationCompleted() {
        _navigationProperty = null
    }
}