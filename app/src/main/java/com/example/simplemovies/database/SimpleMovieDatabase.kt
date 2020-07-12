package com.example.simplemovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDb::class], version = 1, exportSchema = false)
abstract class SimpleMovieDatabase: RoomDatabase() {

    abstract val MovieDao: MovieDao

    companion object {

        @Volatile
        private var INSTANCE: SimpleMovieDatabase? = null

        fun getInstance(context: Context): SimpleMovieDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SimpleMovieDatabase::class.java,
                        "simple_movie_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}