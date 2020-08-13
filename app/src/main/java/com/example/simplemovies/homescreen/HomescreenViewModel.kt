package com.example.simplemovies.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    /**
     * Only on creation of the viewmodel fetch the genres, this prevents a configuration change
     * to call the repository (or API)
     * */
    init {
        fetchMovies()
    }

    /**
     * Subscribe to the repository call and catch it's values
     * */
    fun fetchMovies() {
        viewModelScope.launch {
            movieRepo.getMoviesOfFlow().collect {
               manageMovieResource(it)
            }
        }
    }

    /**
     * Set the API status and data only if not null
     * */
    fun manageMovieResource(resources: Resource<MoviesWrapper>) {
        resources.status?.let { _apiStatus.value = it }
        resources.data?.let { _displayableMovies.value = it.results }
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