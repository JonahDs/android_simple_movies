package com.example.simplemovies.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movies: List<MovieDb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(movie: MovieDb)

    @Query("select * from movies")
    abstract fun getAll(): LiveData<List<MovieDb>>

    @Query("select * from movies")
    abstract fun getAllFlow(): Flow<List<MovieDb>>

    fun getAllFlowDistinc() = getAllFlow().distinctUntilChanged()

}

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genres: List<GenreDb>)

    @Query("select * from genres")
    fun getAll(): LiveData<List<GenreDb>>

}