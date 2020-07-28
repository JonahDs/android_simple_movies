package com.example.simplemovies.search

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchLandingViewModel @Inject constructor(
    private val movierepo: MovieRepository,
    private val genreRepo: GenreRepository
) :
    ViewModel() {

    private val _searchRes = MutableLiveData<MoviesWrapper>()

    val searchRes: LiveData<MoviesWrapper> get() = _searchRes

    private val genres: MutableList<GenreNetwork> = arrayListOf()

    fun search(query: String) {
        viewModelScope.launch {
            _searchRes.value = movierepo.getMoviesOfQuery(query.toLowerCase())
        }
    }

    fun fetchGenres(): LiveData<Resource<GenresWrapper>> {
        return genreRepo.getGenres()
    }

    fun append(genre: GenreNetwork) {
        genres.add(genre)
    }

    fun pop(genre: GenreNetwork) {
        genres.remove(genre)
    }

    fun advancedSearch(query: String) = liveData {
        emit(movierepo.getMoviesOfQuery(query))
    }

    fun filterMovies(movies: MoviesWrapper, state: String) {
        val filteredList = MoviesWrapper(results = arrayListOf())
        if (state == "Include genres") {
            movies.results.forEach {
                if (it.genres!!.any(genres::contains)) {
                    filteredList.results.toMutableList().add(it)
                }
            }
        }
        if (state == "Exclude genres") {
            movies.results.forEach {
                if (!it.genres!!.any(genres::contains)) {
                    filteredList.results.toMutableList().add(it)
                }
            }
        }
        Log.i("TEST", filteredList.toString())
        _searchRes.value = filteredList
    }

}