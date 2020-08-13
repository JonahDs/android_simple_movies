package com.example.simplemovies.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.MovieNetwork

@Entity(tableName = "movies")
data class MovieDb(
    @PrimaryKey
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val runtime: Int?
)

@Entity(tableName = "genres")
data class GenreDb(
    @PrimaryKey
    val id: Int,
    val name: String
)

//region helper function

/**
 * Simple converter, converts a list of movieDb objects to movieNetwork
 * */
fun List<MovieDb>.asMovieNetwork(): List<MovieNetwork> {
    return map {
        MovieNetwork(
            id = it.id,
            vote_count = it.vote_count,
            vote_average = it.vote_average,
            title = it.title,
            release_date = it.release_date,
            poster_path = it.poster_path,
            overview = it.overview,
            genres = null,
            original_name = null,
            runtime = it.runtime
        )
    }
}

/**
 * Simple converter, converts a list of genreDb objects to genreNetwork
 * */
fun List<GenreDb>.asGenreNetwork(): List<GenreNetwork> {
    return map {
        GenreNetwork(
            id = it.id,
            name =  it.name
        )
    }
}
//endregion
