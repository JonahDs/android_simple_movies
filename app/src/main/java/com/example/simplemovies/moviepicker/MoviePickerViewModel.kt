package com.example.simplemovies.moviepicker

import androidx.lifecycle.ViewModel
import com.example.simplemovies.repositories.MovieRepository
import javax.inject.Inject

class MoviePickerViewModel @Inject constructor(private val movieRepo: MovieRepository) : ViewModel() {

}