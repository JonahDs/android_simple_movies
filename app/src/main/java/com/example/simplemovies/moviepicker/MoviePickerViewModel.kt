package com.example.simplemovies.moviepicker

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.invokeCall
import com.example.simplemovies.network.invokeError
import com.example.simplemovies.repositories.MovieRepository
import javax.inject.Inject

class MoviePickerViewModel @Inject constructor(private val movieRepo: MovieRepository) :
    ViewModel() {

    private val _randomMovie = MutableLiveData<MovieNetwork>()

    val randomMovie: LiveData<MovieNetwork> get() = _randomMovie

    private val _navigationProperty = MutableLiveData<Int>()

    val navigationProperty get() = _navigationProperty

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus


    fun fetchRandomMovie(): LiveData<Resource<MoviesWrapper>> =
        liveData(viewModelScope.coroutineContext) {
            try {
                emitSource(invokeCall(movieRepo.getRandomMovie()))
            } catch (e: Exception) {
                emitSource(invokeError())
            }
        }

    fun manageMovieResource(resource: Resource<MoviesWrapper>) {
        resource.data?.let {
            _randomMovie.value = it.results.random()
        }
        resource.status?.let {
            _apiStatus.value = it
        }
    }

    fun navigateToDetail(movieId: Int) {
        _navigationProperty.value = movieId
    }

    fun navigationCompleted() {
        _navigationProperty.value = null
    }

}