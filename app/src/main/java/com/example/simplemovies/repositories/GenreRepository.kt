package com.example.simplemovies.repositories

import com.example.simplemovies.database.GenreDao
import com.example.simplemovies.database.asGenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.asGenreDatabase
import com.example.simplemovies.network.NetworkBounding
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.utils.DataManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val tmdbApiService: TmdbApiService,
    private val genreDao: GenreDao,
    private val dataManager: DataManager
) {
    /**
     * Example repository method that implements NetworkBounding and returns the flow so its
     * subscribable from withing the viewmodel
     *
     * .flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
     * thread from doing to much work
     *
     * @return Flow<Resource<GenresWrapper>>
     * */
    fun getGenres(): Flow<Resource<GenresWrapper>> {
        dataManager.declareTimeout(1, TimeUnit.DAYS)
        return object : NetworkBounding<GenresWrapper>() {
            override fun saveApiResToDb(item: GenresWrapper) {
                genreDao.insert(item.genres.asGenreDatabase())
            }

            override fun shouldFetch(data: GenresWrapper?) =
                data == null || data.genres.isEmpty() || dataManager.shouldRefresh("genres")

            override fun fetchFromDb(): Flow<GenresWrapper?> =
                genreDao.getAllFlowDistinct().map {
                    if (it.isEmpty()) {
                        null
                    } else {
                        GenresWrapper(it.asGenreNetwork())
                    }
                }

            override suspend fun makeApiCall(): GenresWrapper = withContext(IO) {
                tmdbApiService.getAllMovieGenres()
            }
        }.asFlow().flowOn(IO)
    }
}
