package com.example.simplemovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDb::class], version = 1, exportSchema = false)
abstract class SimpleMovieDatabase: RoomDatabase() {
    abstract val MovieDao: MovieDao
}