package com.example.simplemovies.repositories

import android.util.Log
import androidx.lifecycle.*
import com.example.simplemovies.database.GenreDao
import com.example.simplemovies.database.asGenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.asGenreDatabase
import com.example.simplemovies.network.NetworkBounding
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val tmdbApiService: TmdbApiService,
    private val genreDao: GenreDao
) {

    private val repoJob = Job()

    private val scope = CoroutineScope(repoJob + Main)

    private val dataManager = DataManager(30, TimeUnit.SECONDS)

    fun getGenres(): LiveData<Resource<GenresWrapper>> {
        return object : NetworkBounding<GenresWrapper, GenresWrapper>() {
            override fun saveApiResToDb(item: GenresWrapper) {
                scope.launch {
                    withContext(IO) {
                        genreDao.insert(item.genres.asGenreDatabase())
                    }
                }
            }

            override fun shouldFetch(data: GenresWrapper?): Boolean {
                return data == null || data.genres.isEmpty() || data.genres == null
            }

            override fun fetchFromDb(): LiveData<GenresWrapper>  {
                val test = MediatorLiveData<GenresWrapper>()
                scope.launch {
                    withContext(Main) {
                        test.addSource(genreDao.getAll()){
                            test.removeSource(genreDao.getAll())
                            test.value = GenresWrapper(it.asGenreNetwork())
                        }
                    }
                }
                return test
            }

            override fun makeApiCall(): LiveData<GenresWrapper> = liveData {
                try {
                    emit(tmdbApiService.getAllMovieGenres())
                } catch (e: Exception) {
                    Log.i("GENRE_REPO", e.message.toString())
                }
            }
        }.asLiveData()
    }

}