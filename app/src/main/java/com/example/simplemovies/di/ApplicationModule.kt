package com.example.simplemovies.di

import android.content.Context
import androidx.room.Room
import com.example.simplemovies.database.SimpleMovieDatabase
import com.example.simplemovies.network.TmdbApiService
import com.example.simplemovies.repositories.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    //Provide our movieRepo
    @Provides
    fun provideRepo(service: TmdbApiService, database: SimpleMovieDatabase): MovieRepository {
        return MovieRepository(service, database.MovieDao)
    }

    //Provide our database
    @Provides
    fun provideDatabas(context: Context): SimpleMovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SimpleMovieDatabase::class.java,
            "simple_movie_database"
        ).fallbackToDestructiveMigration().build()
    }

}