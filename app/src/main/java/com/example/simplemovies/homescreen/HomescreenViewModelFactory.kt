package com.example.simplemovies.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplemovies.database.MovieDao

class HomescreenViewModelFactory(private val movieDao: MovieDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomescreenViewModel::class.java)) {
            return HomescreenViewModel(movieDao) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}