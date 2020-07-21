package com.example.simplemovies.di

import android.content.Context
import androidx.room.Room
import com.example.simplemovies.database.SimpleMovieDatabase
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    //Provide our movieRepo
    @Singleton
    @JvmStatic
    @Provides
    fun provideMovieRepo(service: TmdbApiService, database: SimpleMovieDatabase): MovieRepository {
        return MovieRepository(service, database.movieDao)
    }

    fun provideGenreRepo(service: TmdbApiService, database: SimpleMovieDatabase): GenreRepository {
        return GenreRepository(service, database.genreDao)
    }

    //Provide our database
    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabas(context: Context): SimpleMovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SimpleMovieDatabase::class.java,
            "simple_movie_database"
        ).fallbackToDestructiveMigration().build()
    }

}