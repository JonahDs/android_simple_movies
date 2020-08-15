package com.example.simplemovies.experimental


import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    /**
     * Only on creation of the viewmodel fetch the genres, this prevents a configuration change
     * to call the repository (or API)
     * */
    init {
        fetchGenres()
    }

    /**
     * Subscribe to the repository call and catch it's values
     * */
    private fun fetchGenres() {
        viewModelScope.launch {
            genreRepository.getGenres().collect {
                manageGenreResources(it)
            }
        }
    }

    /**
     * Subscribe to the repository call and catch it's values.
     *
     * Create a 'settings' or configuration object based on given parameters.
     *
     * genresInc = state is include? give checked chips, otherwise none
     * genresExl = state is exclude? give checked chips, otherwise none
     *
     * @param type fetch type, movie or tv
     * @param state include/exclude genres
     * @param score wanted user score
     * @param resource Android resources to check on types
     * */
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
    /**
     * Set the API status and data only if not null
     *
     * @param resources wrapper for the API result
     * */
    private fun manageGenreResources(resources: Resource<GenresWrapper>) {
        resources.data?.let { _genres.value = it }
        resources.status?.let { _apiStatus.value = it }
    }

    /**
     * Set the API status and data only if not null
     *
     * @param resource wrapper for teh API result
     * */
    private fun manageDiscoverResource(resource: Resource<MoviesWrapper>) {
        resource.status?.let { _apiStatus.value = it }
        resource.data?.let { _discover.value = it }
    }

    /**
     * Make a configuration object/ settings to make the API call
     *
     * @param type fetch type, movie or tv
     * @param state include/exclude genres
     * @param score wanted user score
     * @param resource Android resources to check on types
     * */
    private fun discoverManager(state: String, type: String, score: String, resource: Resources) =
        object {
            val state =
                resource.getStringArray(R.array.include_states).firstOrNull { t -> t == state }
            val type = resource.getStringArray(R.array.types).firstOrNull { t -> t == type }
            val score = resource.getStringArray(R.array.user_scores).firstOrNull { t -> t == score }
                ?.filter { it -> it.isDigit() }
        }

    /**
     * Manage the chips, if checked is true then add it, else remove it
     *
     * @param genre the genre object
     * @param isChecked boolean if the chip is clicked or not
     * */
    fun manageChips(genre: GenreNetwork, isChecked: Boolean) {
        if (isChecked) {
            _checkedChips.add(genre.id.toString())
        } else {
            _checkedChips.remove(genre.id.toString())
        }
    }


    /**
     * Set navigation property to the clicked id
     * */
    fun navSelected(id: Int) {
        _navProperty.value = id
    }

    /**
     * Clear the navigation property
     * */
    fun navCompleted() {
        _navProperty.value = null
    }



}
