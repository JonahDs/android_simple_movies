package com.example.simplemovies.database

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

    /**
     * We don't want any unneeded overhead, by nature a flow will always trigger whenever any
     * row in the table is updated. By calling distinctUntilChanged we ensure that the UI will only
     * get updated when the data actually changes
     * */
    fun getAllFlowDistinct() = getAll().distinctUntilChanged()

}

@Dao
abstract class GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(genres: List<GenreDb>)

    @Query("select * from genres")
    abstract fun getAll(): Flow<List<GenreDb>>

    /**
     * We don't want any unneeded overhead, by nature a flow will always trigger whenever any
     * row in the table is updated. By calling distinctUntilChanged we ensure that the UI will only
     * get updated when the data actually changes
     * */
    fun getAllFlowDistinct() = getAll().distinctUntilChanged()
}