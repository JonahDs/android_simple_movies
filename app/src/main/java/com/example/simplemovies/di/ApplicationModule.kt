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

    /**
     * Tell Dagger how to create the <returntype>
     * @Singleton to only create one instance inside given scope
     * @JvmStatic compile to a static function for performance increase
     * @Provides tell Dagger that this method needs to be called when <returntype> is needed
     * */
    @Singleton
    @JvmStatic
    @Provides
    fun provideMovieRepo(service: TmdbApiService, database: SimpleMovieDatabase, dataManager: DataManager): MovieRepository {
        return MovieRepository(service, database.movieDao, dataManager)
    }

    /**
     * Tell Dagger how to create the <returntype>
     * @Singleton to only create one instance inside given scope
     * @JvmStatic compile to a static function for performance increase
     * @Provides tell Dagger that this method needs to be called when <returntype> is needed
     * */
    @Singleton
    @JvmStatic
    @Provides
    fun provideGenreRepo(service: TmdbApiService, database: SimpleMovieDatabase, dataManager: DataManager): GenreRepository {
        return GenreRepository(service, database.genreDao, dataManager)
    }

    /**
     * Tell Dagger how to create the <returntype>
     * @Singleton to only create one instance inside given scope
     * @JvmStatic compile to a static function for performance increase
     * @Provides tell Dagger that this method needs to be called when <returntype> is needed
     * */
    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): SimpleMovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SimpleMovieDatabase::class.java,
            "simple_movie_database"
        ).fallbackToDestructiveMigration().build()
    }
}
