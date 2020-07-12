package com.example.simplemovies.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieDb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: MovieDb)

    @Query("select * from movies")
    fun getAll(): LiveData<List<MovieDb>>

}