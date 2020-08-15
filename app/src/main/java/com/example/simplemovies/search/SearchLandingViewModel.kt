package com.example.simplemovies.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
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

    // properties
    private var query: String = ""

    /**
     * Set the <properties> only if parameters are not equal or untouched (null, "").
     * This check prevents a configuration change to call the repository (and possibly the API)
     * again while in no usecase this should happen.
     *
     * @param query search query
     * */
    fun setQuery(query: String) {
        if (this.query == "") {
            this.query = query
            fetchFromQuery(query)
        }
    }

    /**
     * Subscribe to the repository call and catch it's values
     *
     * @param query search string
     * */
    private fun fetchFromQuery(query: String) {
        viewModelScope.launch {
            movierepo.getMoviesOfQuery(query).collect {
                manageMovieResource(it)
            }
        }
    }

    /**
     * Set the API status and data only if not null
     *
     * @param resource wrapper for the API result
     * */
    private fun manageMovieResource(resource: Resource<MoviesWrapper>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _searchRes.value = it }
    }

    /**
     * Sets the navigation property to the clicked id
     * */
    fun navigateToDetail(id: Int) {
        _navigation.value = id
    }

    /**
     * Clear the navigation
     * */
    fun navigationCompleted() {
        _navigation.value = null
    }
}
