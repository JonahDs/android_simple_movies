package com.example.simplemovies.cast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastViewmodel @Inject constructor(private val movierepo: MovieRepository) : ViewModel(){

    private var movieId: Int? = null
    private var type: String? = null

    private val _cast = MutableLiveData<Cast>()

    val cast: LiveData<Cast> get() = _cast

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    fun setMovieId(movieId: Int, type: String) {
        if(this.movieId != movieId && this.type != type) {
            this.movieId = movieId
            this.type = type
            fetchCredits(movieId, type)
        }
    }

    private fun fetchCredits(movieId: Int, type: String) {
        viewModelScope.launch {
            movierepo.getMovieCast(type, movieId).collect {
                Log.i("CAST", it.status.toString() + it.data.toString())
                manageCastResource(it)
            }
        }

    }

    private fun manageCastResource(resource: Resource<Cast>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _cast.value = it }
    }

}