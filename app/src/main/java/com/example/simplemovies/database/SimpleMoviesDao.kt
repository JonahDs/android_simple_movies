package com.example.simplemovies.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movies: List<MovieDb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(movie: MovieDb)

    @Query("select * from movies order by id ASC")
    abstract fun getAll(): Flow<List<MovieDb>>

    @Query("delete from movies")
    abstract fun clearTable()

    fun getAllFlowDistinct() = getAll().distinctUntilChanged()

}

@Dao
abstract class GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(genres: List<GenreDb>)

    @Query("select * from genres")
    abstract fun getAll(): Flow<List<GenreDb>>

    fun getAllFlowDistinct() = getAll().distinctUntilChanged()
}