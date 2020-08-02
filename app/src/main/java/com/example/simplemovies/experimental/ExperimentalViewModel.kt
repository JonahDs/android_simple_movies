package com.example.simplemovies.experimental

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.R
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.invokeCall
import com.example.simplemovies.network.invokeError
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExperimentalViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {

    val fetchedGenres = genreRepository.getGenres()

    private val _checkedChips: MutableList<String> = arrayListOf()

    private val _discover = MutableLiveData<MoviesWrapper>()

    val discover: LiveData<MoviesWrapper> get() = _discover

    private val _apiStatus = MutableLiveData<APIStatus>()

    val apiStatus: LiveData<APIStatus> get() = _apiStatus

    private val _navProperty = MutableLiveData<Int>()

    val navProperty: LiveData<Int> get() = _navProperty

    fun fetchDiscover(
        state: String,
        type: String,
        score: String,
        resource: Resources
    ): LiveData<Resource<MoviesWrapper>> = liveData(viewModelScope.coroutineContext) {
        val settings = discoverManager(state, type, score, resource)
        try {
            emitSource(
                invokeCall(
                    movieRepository.getDiscover(
                        type = settings.type?.toLowerCase(),
                        genresInc = if (settings.state == resource.getStringArray(R.array.include_states)[0]) _checkedChips else listOf(),
                        genresExl = if (settings.state == resource.getStringArray(R.array.include_states)[1]) _checkedChips else listOf(),
                        score = if (settings.score != null && settings.score != "") settings.score.toInt() else 0
                    )
                )
            )
        } catch (e: Exception) {
            emitSource(invokeError())
        }
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
        Log.i("NAV_PROP", id.toString())
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
