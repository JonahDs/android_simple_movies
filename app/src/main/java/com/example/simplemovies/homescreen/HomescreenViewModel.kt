package com.example.simplemovies.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplemovies.domain.PopularMovies
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.TmdbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomescreenViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _response = MutableLiveData<PopularMovies>()

    val response: LiveData<PopularMovies> get() = _response

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    //Navigation property
    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        scope.launch {
            try {
                _apiStatus.value = APIStatus.LOADING
                _response.value = TmdbApi.retrofitService.getPopularMovies("eebddf3c28edf2691214c6ece5688e32")
                _apiStatus.value = APIStatus.DONE
            }catch (e: Exception) {
                _apiStatus.value = APIStatus.ERROR
                _response.value = PopularMovies(ArrayList())
            }
        }
    }

    fun displayMovieDetails(movieId: Int) {
        _navSelected.value = movieId
    }

    fun displayMovieCompleted() {
        _navSelected.value = null
    }

}