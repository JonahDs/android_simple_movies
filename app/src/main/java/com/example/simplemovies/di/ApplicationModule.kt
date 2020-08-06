package com.example.simplemovies.di

import android.content.Context
import androidx.room.Room
import com.example.simplemovies.database.SimpleMovieDatabase
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.repositories.GenreRepository
import com.example.simplemovies.repositories.MovieRepository
import com.example.simplemovies.utils.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    //Provide our movieRepo
    @Singleton
    @JvmStatic
    @Provides
    fun provideMovieRepo(service: TmdbApiService, database: SimpleMovieDatabase, dataManager: DataManager): MovieRepository {
        return MovieRepository(service, database.movieDao, dataManager)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideGenreRepo(service: TmdbApiService, database: SimpleMovieDatabase, dataManager: DataManager): GenreRepository {
        return GenreRepository(service, database.genreDao, dataManager)
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