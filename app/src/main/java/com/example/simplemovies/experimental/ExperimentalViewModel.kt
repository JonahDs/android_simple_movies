package com.example.simplemovies.experimental

import androidx.lifecycle.ViewModel
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import javax.inject.Inject

class ExperimentalViewModel @Inject constructor(private val movieRepository: MovieRepository, private val genreRepository: GenreRepository) : ViewModel() {

    val fetchedGenres = genreRepository.getGenres()
}
