package com.example.simplemovies.database

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplemovies.domain.Result

@Entity(tableName = "movies")
data class MovieDb(
    @PrimaryKey(autoGenerate = true)
    val objectId: Long = 0L,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

fun List<MovieDb>.asDomainObject(): List<Result> {
    return map {
        Result(
            id = it.id,
            vote_count = it.vote_count,
            vote_average = it.vote_average,
            title = it.title,
            release_date = it.release_date,
            poster_path = it.poster_path,
            overview = it.overview
        )
    }
}