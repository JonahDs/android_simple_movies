package com.example.simplemovies.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailscreenViewModelFactory(private val movieId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailscreenViewModel::class.java)) {
            return DetailscreenViewModel(movieId) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}