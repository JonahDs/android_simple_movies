package com.example.simplemovies.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchLandingViewModel @Inject constructor(private val movierepo: MovieRepository) :
    ViewModel() {

    fun search(query: String) {
        viewModelScope.launch {
            val res = movierepo.getMoviesOfQuery(query.toLowerCase())
            Log.i("VIEWMODE_RES", res.results.toString())
        }
    }
}