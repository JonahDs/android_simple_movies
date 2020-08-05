package com.example.simplemovies.experimental


import android.content.res.Resources
import androidx.lifecycle.*
import com.example.simplemovies.R
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExperimentalViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val _checkedChips: MutableList<String> = arrayListOf()

    private val _discover = MutableLiveData<MoviesWrapper>()

    val discover: LiveData<MoviesWrapper> get() = _discover

    private val _genres = MutableLiveData<GenresWrapper>()

    val genres: LiveData<GenresWrapper> get() = _genres

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    private val _navProperty = MutableLiveData<Int>()

    val navProperty: LiveData<Int> get() = _navProperty


    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            genreRepository.getGenres().collect {
                manageGenreResources(it)
            }
        }
    }

    fun fetchDiscover(
        state: String,
        type: String,
        score: String,
        resource: Resources
    ) {
        val settings = discoverManager(state, type, score, resource)
        viewModelScope.launch {
            movieRepository.getDiscover(
                type = settings.type?.toLowerCase(),
                genresInc = if (settings.state == resource.getStringArray(R.array.include_states)[0]) _checkedChips else listOf(),
                genresExl = if (settings.state == resource.getStringArray(R.array.include_states)[1]) _checkedChips else listOf(),
                score = if (settings.score != null && settings.score != "") settings.score.toInt() else 0
            ).collect {
                manageDiscoverResource(it)
            }
        }
    }

    fun manageGenreResources(resources: Resource<GenresWrapper>) {
        resources.data?.let { _genres.value = it }
        resources.status?.let { _apiStatus.value = it }
    }

    fun manageDiscoverResource(resource: Resource<MoviesWrapper>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _discover.value = it }
    }

    fun manageChips(genre: GenreNetwork, isChecked: Boolean) {
        if (isChecked) {
            _checkedChips.add(genre.id.toString())
        } else {
            _checkedChips.remove(genre.id.toString())
        }
    }

    fun navSelected(id: Int) {
        _navProperty.value = id
    }

    fun navCompleted() {
        _navProperty.value = null
    }

    private fun discoverManager(state: String, type: String, score: String, resource: Resources) =
        object {
            val state =
                resource.getStringArray(R.array.include_states).firstOrNull { t -> t == state }
            val type = resource.getStringArray(R.array.types).firstOrNull { t -> t == type }
            val score = resource.getStringArray(R.array.user_scores).firstOrNull { t -> t == score }
                ?.filter { it -> it.isDigit() }
        }


}
