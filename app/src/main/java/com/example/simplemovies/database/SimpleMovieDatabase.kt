package com.example.simplemovies.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDb::class, GenreDb::class], version = 1, exportSchema = false)
abstract class SimpleMovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val genreDao: GenreDao
}