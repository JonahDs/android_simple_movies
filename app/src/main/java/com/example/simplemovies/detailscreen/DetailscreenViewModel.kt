package com.example.simplemovies.detailscreen

import androidx.lifecycle.*
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.invokeCall
import com.example.simplemovies.network.invokeError
import com.example.simplemovies.repositories.MovieRepository
import javax.inject.Inject

class DetailscreenViewModel @Inject constructor(private val movieRepo: MovieRepository) :
    ViewModel() {

    private val _result = MutableLiveData<MovieResult>()

    val result: LiveData<MovieResult> get() = _result

    private val _movieCast = MutableLiveData<Cast>()

    val movieCast: LiveData<Cast> get() = _movieCast

    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected

    private val _detailStatus = MutableLiveData<APIStatus>()

    val detailStatus: LiveData<APIStatus> get() = _detailStatus

    private val _castStatus = MutableLiveData<APIStatus>()

    val castStatus: LiveData<APIStatus> get() = _castStatus


    fun manageDetailResource(resource: Resource<MovieResult>) {
        resource.status?.let { _detailStatus.value = it }
        resource.data?.let { _result.value = it }
    }

    fun manageCastResource(resource: Resource<Cast>) {
        resource.status?.let { _castStatus.value = it }
        resource.data?.let { _movieCast.value = it }
    }


    fun getMovieDetails(type: String, id: Int): LiveData<Resource<MovieResult>> =
        liveData(viewModelScope.coroutineContext) {
            try {
                emitSource(invokeCall(movieRepo.getMovieDetails(type, id)))
            } catch (e: Exception) {
                emitSource(invokeError())
            }
        }

    fun getMovieCast(type: String, id: Int): LiveData<Resource<Cast>> =
        liveData(viewModelScope.coroutineContext) {
            try {
                emitSource(invokeCall(movieRepo.getMovieCast(type, id)))
            } catch (e: Exception) {
                emitSource(invokeError())
            }
        }


    fun displayCastDetails(castId: Int) {
        _navSelected.value = castId
    }

    fun displayCastDetailsCompleted() {
        _navSelected.value = null
    }
}