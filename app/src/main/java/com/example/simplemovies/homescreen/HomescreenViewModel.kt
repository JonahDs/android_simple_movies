package com.example.simplemovies.homescreen

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

//constructor inject a movieRepository
class HomescreenViewModel @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

    private val _displayableMovies = MutableLiveData<List<MovieNetwork>>()

    val displayableMovies: LiveData<List<MovieNetwork>> get() = _displayableMovies

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    //Navigation property
    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected


    //Set navigation property
    fun displayMovieDetails(movieId: Int) {
        _navSelected.value = movieId
    }

    //Clear navigation to make 'random' navigation impossible
    fun displayMovieCompleted() {
        _navSelected.value = null
    }

    //Pass repo function to attach observer in fragment
    fun fetchMovies(): LiveData<Resource<MoviesWrapper>> = liveData (viewModelScope.coroutineContext) {
        try {
            emitSource(movieRepo.getMoviesOfFlow())
        }catch (e: Exception) {

        }
    }

    //Manage fragment state
    fun manageState(resource: Resource<MoviesWrapper>) {
        if(resource.status != null) {
            setState(resource.status)
        }
        if(resource.data != null) {
            displayMovies(resource.data.results)
        }
    }

    //Set movies
    private fun displayMovies(movies: List<MovieNetwork>) {
        _displayableMovies.value = movies
    }

    //Set API/App status
    private fun setState(state: APIStatus) {
        _apiStatus.value = state
    }
}