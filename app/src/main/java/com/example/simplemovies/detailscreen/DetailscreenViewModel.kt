package com.example.simplemovies.detailscreen

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailscreenViewModel @Inject constructor(private val movieRepo: MovieRepository) :
    ViewModel() {

    private val _result = MutableLiveData<MovieResult>()

    val result: LiveData<MovieResult> get() = _result

    private val _movieCast = MutableLiveData<Cast>()

    val movieCast: LiveData<Cast> get() = _movieCast

    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    private var state: String = ""

    private var id: Int = 0

    fun setState(state: String, id: Int) {
        if(this.state == "" && this.id == 0) {
            this.state = state
            this.id = id
            fetchMovieDetails(state, id)
            fetchMovieCredits(state, id)
        }
    }

    private fun fetchMovieDetails(type: String, id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieDetails(type, id).collect {
                manageDetailResource(it)
            }
        }
    }

    private fun fetchMovieCredits(type: String, id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieCast(type, id).collect {
                manageCastResource(it)
            }
        }
    }

    private fun manageDetailResource(resource: Resource<MovieResult>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _result.value = it }
    }

    private fun manageCastResource(resource: Resource<Cast>) {
        resource.data?.let { _movieCast.value = it }
    }

    fun displayCastDetails(castId: Int) {
        _navSelected.value = castId
    }

    fun displayCastDetailsCompleted() {
        _navSelected.value = null
    }
}