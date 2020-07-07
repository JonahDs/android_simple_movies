package com.example.simplemovies.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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