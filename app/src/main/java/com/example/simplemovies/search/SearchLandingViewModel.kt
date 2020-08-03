package com.example.simplemovies.search

import androidx.lifecycle.*
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.invokeCall
import com.example.simplemovies.network.invokeError
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import javax.inject.Inject

class SearchLandingViewModel @Inject constructor(
    private val movierepo: MovieRepository,
    private val genreRepo: GenreRepository
) :
    ViewModel() {

    private val _searchRes = MutableLiveData<MoviesWrapper>()

    val searchRes: LiveData<MoviesWrapper> get() = _searchRes

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    private val _navigation = MutableLiveData<Int>()

    val navigation: LiveData<Int> get() = _navigation

    fun fetchFromQuery(query: String): LiveData<Resource<MoviesWrapper>> =
        liveData(viewModelScope.coroutineContext) {
            try {
                emitSource(invokeCall(movierepo.getMoviesOfQuery(query)))
            } catch (e: Exception) {
                emitSource((invokeError()))
            }
        }

    fun manageMovieResource(resource: Resource<MoviesWrapper>) {
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