package com.example.simplemovies.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchLandingViewModel @Inject constructor(private val movierepo: MovieRepository, private val genreRepo: GenreRepository) :
    ViewModel() {

    private val _searchRes = MutableLiveData<MoviesWrapper>()

    val searchRes: LiveData<MoviesWrapper> get() = _searchRes

    fun search(query: String) {
        viewModelScope.launch {
            _searchRes.value = movierepo.getMoviesOfQuery(query.toLowerCase())
        }
    }

    fun fetchGenres(): LiveData<Resource<GenresWrapper>>{
        return genreRepo.getGenres()
    }

}