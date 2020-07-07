package com.example.simplemovies.homescreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.asDomainObject
import com.example.simplemovies.domain.Result
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.*

class HomescreenViewModel(movieDao: MovieDao) : ViewModel() {

    private var viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _response = MutableLiveData<List<Result>>()

    val response: LiveData<List<Result>> get() = _response

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    //Navigation property
    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected

    private var movieRepo: MovieRepository

    init {
        getPopularMovies()
        movieRepo = MovieRepository(movieDao)
    }

    private fun getPopularMovies() {
        scope.launch {
            try {
                _apiStatus.postValue(APIStatus.LOADING)
                _response.value = withContext(Dispatchers.IO) {
                    movieRepo.getMovies()
                }.asDomainObject()

                _apiStatus.postValue(APIStatus.DONE)

            } catch (e: Exception) {
                Log.i("VIEWMODEL_ERROR", e.message.toString())
                _apiStatus.postValue(APIStatus.ERROR)
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