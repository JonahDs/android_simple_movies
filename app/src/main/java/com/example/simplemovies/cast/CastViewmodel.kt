package com.example.simplemovies.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.CastWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastViewmodel @Inject constructor(private val movierepo: MovieRepository) : ViewModel() {

    // region properties
    private var movieId: Int? = null
    private var type: String? = null
    //endregion

    private val _cast = MutableLiveData<CastWrapper>()

    val cast: LiveData<CastWrapper> get() = _cast

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    /**
     * Set the <properties> only if parameters are not equal or untouched (null, "").
     * This check prevents a configuration change to call the repository (and possibly the API)
     * again while in no usecase this should happen.
     *
     * @param movieId the movie or tv id
     * @param type fetch type: movie, tv
     * */
    fun setMovieId(movieId: Int, type: String) {
        if (this.movieId != movieId && this.type != type) {
            this.movieId = movieId
            this.type = type
            fetchCredits(movieId, type)
        }
    }

    /**
     * Subscribe to the repository call and catch it's values
     *
     * @param movieId the movie or tv id
     * @param type fetch type: movie, tv
     * */
    private fun fetchCredits(movieId: Int, type: String) {
        viewModelScope.launch {
            movierepo.getMovieCast(type, movieId).collect {
                manageCastResource(it)
            }
        }

    }

    /**
     * Set the API status and data only if not null
     *
     * @param resource wrapper for the api return
     * */
    private fun manageCastResource(resource: Resource<CastWrapper>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _cast.value = it }
    }

}