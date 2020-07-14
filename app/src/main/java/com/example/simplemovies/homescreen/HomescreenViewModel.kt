package com.example.simplemovies.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.domain.PopularMovies
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.MovieRepository
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

    //Set movies
    fun displayMovies(movies: List<MovieNetwork>) {
        _displayableMovies.value = movies
    }

    //Set API/App status
    fun setState(state: APIStatus) {
        _apiStatus.value = state
    }

    //Pass repo function to attach observer in fragment
    fun fetchMovies(): LiveData<Resource<PopularMovies>> {
        return movieRepo.getMovies()
    }
}