package com.example.simplemovies.homescreen

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//constructor inject a movieRepository
class HomescreenViewModel @Inject constructor(private val movieRepo: MovieRepository) :
    ViewModel() {

    private val _displayableMovies = MutableLiveData<List<MovieNetwork>>()

    val displayableMovies: LiveData<List<MovieNetwork>> get() = _displayableMovies

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    //Navigation property
    private val _navSelected = MutableLiveData<Int>()

    val navSelected: LiveData<Int> get() = _navSelected


    init {
        getMoviesStatic()
    }



    fun getMoviesStatic() {
        viewModelScope.launch {
            movieRepo.getMoviesOfFlow().collect {
                it.data?.let { wrapper ->
                    _displayableMovies.value = wrapper.results
                }
                it.status?.let { state ->
                    Log.i("API_STATE", state.toString())
                    _apiStatus.value = state
                }

            }
        }
    }


    //Set navigation property
    fun displayMovieDetails(movieId: Int) {
        _navSelected.value = movieId
    }

    //Clear navigation to make 'random' navigation impossible
    fun displayMovieCompleted() {
        _navSelected.value = null
    }


    fun clearMovies() {
        _displayableMovies.value = listOf()
    }

}