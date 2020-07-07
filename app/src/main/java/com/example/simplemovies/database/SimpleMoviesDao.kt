package com.example.simplemovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: MovieDb)

    @Update
    fun update(movie: MovieDb)

    @Query("select * from movies")
    fun getAll(): List<MovieDb>

}