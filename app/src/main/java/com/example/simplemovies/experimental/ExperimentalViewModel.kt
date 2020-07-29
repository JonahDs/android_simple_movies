package com.example.simplemovies.experimental

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplemovies.R
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

class ExperimentalViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {

    val fetchedGenres = genreRepository.getGenres()

    private val _checkedChips: MutableList<String> = arrayListOf()

    fun discover(state: String, type: String, score: String, resource: Resources) {
        val settings = discoverManager(state, type, score, resource)
        viewModelScope.launch {
            try {
                movieRepository.getDiscover(settings.type?.toLowerCase(), _checkedChips, settings.score)
            } catch (e: Exception) {
                Log.i("EXP_EXC", e.message.toString())
            }
        }
    }

    fun manageChips(genre: GenreNetwork, isChecked: Boolean) {
        if (isChecked) {
            _checkedChips.add(genre.id.toString())
        } else {
            _checkedChips.remove(genre.id.toString())
        }
    }

    private fun discoverManager(state: String, type: String, score: String, resource: Resources) = object {
        val state = resource.getStringArray(R.array.include_states).firstOrNull{t -> t == state}
        val type = resource.getStringArray(R.array.types).firstOrNull{t -> t== type}
        val score = resource.getStringArray(R.array.user_scores).firstOrNull{t -> t == score}?.filter { it -> it.isDigit() }?.toInt()
    }
}
