package com.example.simplemovies.search

import androidx.lifecycle.*
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.invokeCall
import com.example.simplemovies.network.invokeError
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchLandingViewModel @Inject constructor(
    private val movierepo: MovieRepository
) :
    ViewModel() {

    private val _searchRes = MutableLiveData<MoviesWrapper>()

    val searchRes: LiveData<MoviesWrapper> get() = _searchRes

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    private val _navigation = MutableLiveData<Int>()

    val navigation: LiveData<Int> get() = _navigation

    private var query: String = ""

    fun setQuery(query: String) {
        if(this.query == "") {
            this.query = query
            fetchFromQuery(query)
        }
    }

    private fun fetchFromQuery(query: String) {
        viewModelScope.launch {
            movierepo.getMoviesOfQuery(query).collect {
                manageMovieResource(it)
            }
        }
    }

    private fun manageMovieResource(resource: Resource<MoviesWrapper>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _searchRes.value = it }
    }

    fun navigateToDetail(id: Int) {
        _navigation.value = id
    }

    fun navigationCompleted() {
        _navigation.value = null
    }
}