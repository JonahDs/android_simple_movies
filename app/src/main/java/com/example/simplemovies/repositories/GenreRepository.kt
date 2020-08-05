package com.example.simplemovies.repositories

import com.example.simplemovies.database.GenreDao
import com.example.simplemovies.database.asGenreNetwork
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.asGenreDatabase
import com.example.simplemovies.network.NetworkBoundingNew
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
    private val genreDao: GenreDao
) {
    private val dataManager = DataManager(30, TimeUnit.SECONDS)

    fun getGenres(): Flow<Resource<GenresWrapper>> {
        return object : NetworkBoundingNew<GenresWrapper>() {
            override fun saveApiResToDb(item: GenresWrapper) {
                genreDao.insert(item.genres.asGenreDatabase())
            }

            override fun shouldFetch(data: GenresWrapper?) =
                data == null || data.genres.isEmpty()


            override fun fetchFromDb(): Flow<GenresWrapper> =
                genreDao.getAllFlowDistinct().map { GenresWrapper(it.asGenreNetwork()) }


            override suspend fun makeApiCall(): GenresWrapper = withContext(IO) {
                tmdbApiService.getAllMovieGenres()
            }
        }.asFlow().flowOn(IO)
    }

}