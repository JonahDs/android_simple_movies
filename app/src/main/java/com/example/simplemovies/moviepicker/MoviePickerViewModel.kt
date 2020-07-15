package com.example.simplemovies.moviepicker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePickerViewModel @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

    private val viewModelJob = Job()

    private val scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _movieGenres = MutableLiveData<GenresWrapper>()

    val movieGenres : LiveData<GenresWrapper> get() = _movieGenres

    private var _moviesWithGenre = MutableLiveData<MovieNetwork>()

    val moviesWithGenre: LiveData<MovieNetwork> get() = _moviesWithGenre

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        scope.launch {
            try {
                _movieGenres.value = movieRepo.getAllMovieGenres()
            } catch (e: Exception) {
                Log.i("GENRES_EX", e.message.toString())
            }
        }
    }

    fun fetchMovieById(genreId: Int) {
        scope.launch {
            try {
                _moviesWithGenre.value =  movieRepo.getMoviesWithGenre(genreId.toString()).results.random()
            } catch (e: Exception) {
                Log.i("GENRES_EX_", e.message.toString())

            }
        }
    }
}