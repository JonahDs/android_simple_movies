package com.example.simplemovies.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplemovies.database.MovieDao
import java.lang.IllegalArgumentException

class DetailscreenViewModelFactory(private val movieId: Int, private val movieDao: MovieDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailscreenViewModel::class.java)) {
            return DetailscreenViewModel(movieId, movieDao) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}