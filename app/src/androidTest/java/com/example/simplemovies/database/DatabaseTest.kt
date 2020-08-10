package com.example.simplemovies.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.domain.asGenreDatabase
import com.example.simplemovies.domain.asMovieDatabase
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var database: SimpleMovieDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var genreDao: GenreDao

    private lateinit var movies: MoviesWrapper
    private lateinit var genres: GenresWrapper

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context, SimpleMovieDatabase::class.java).allowMainThreadQueries().build()

        movies = Gson().fromJson(jsonMovie, MoviesWrapper::class.java)
        genres = Gson().fromJson(jsonGenres, GenresWrapper::class.java)

        movieDao = database.movieDao
        genreDao = database.genreDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertMovies() {
        movieDao.insert(movies.results.asMovieDatabase())
        val res = runBlocking {
            movieDao.getAll().map { it.asMovieNetwork() }.first().sortedBy { it.id }
        }
        Assert.assertEquals(movies.results.sortedBy { it.id }, res)
    }

    @Test
    @Throws(IOException::class)
    fun deleteMovies() {
        movieDao.clearTable()
        val res = runBlocking {
            movieDao.getAll().first()
        }
        Assert.assertTrue(res.isEmpty())
    }

    @Test
    @Throws(IOException::class)
    fun insertGenres(){
        genreDao.insert(genres.genres.asGenreDatabase())
        val res = runBlocking {
            genreDao.getAll().map {it.asGenreNetwork()}.first().sortedBy { it.id }
        }
        Assert.assertEquals(genres.genres.sortedBy { it.id }, res)
    }
}