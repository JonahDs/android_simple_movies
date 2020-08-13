package com.example.simplemovies.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.CastWrapper
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

    private val _movieCast = MutableLiveData<CastWrapper>()

    val movieCast: LiveData<CastWrapper> get() = _movieCast

    private val _navSelected = MutableLiveData<Boolean>()

    val navSelected: LiveData<Boolean> get() = _navSelected

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    // region properties
    private var state: String = ""
    private var id: Int = 0
    //endregion

    /**
     * Set the <properties> only if parameters are not equal or untouched (null, "").
     * This check prevents a configuration change to call the repository (and possibly the API)
     * again while in no usecase this should happen.
     *
     * @param type fetch type, movie or tv
     * @param id movie or tv id
     * */
    fun setState(type: String, id: Int) {
        if(this.state == "" && this.id == 0) {
            this.state = type
            this.id = id
            fetchMovieDetails(type, id)
            fetchMovieCredits(type, id)
        }
    }

    /**
     * Subscribe to the repository call and catch it's values
     * */
    private fun fetchMovieDetails(type: String, id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieDetails(type, id).collect {
                manageDetailResource(it)
            }
        }
    }

    /**
     * Subscribe to the repository call and catch it's values
     * */
    private fun fetchMovieCredits(type: String, id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieCast(type, id).collect {
                manageCastResource(it)
            }
        }
    }

    /**
     * Set the API status and data only if not null
     * */
    private fun manageDetailResource(resource: Resource<MovieResult>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _result.value = it }
    }

    /**
     * Set the data only if not null
     * */
    private fun manageCastResource(resource: Resource<CastWrapper>) {
        resource.data?.let { _movieCast.value = it }
    }


    fun displayCastDetails() {
        _navSelected.value = true
    }

    fun displayCastDetailsCompleted() {
        _navSelected.value = null
    }
}