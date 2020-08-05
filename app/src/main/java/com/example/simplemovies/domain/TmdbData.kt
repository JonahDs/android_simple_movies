package com.example.simplemovies.domain

import com.example.simplemovies.database.GenreDb
import com.example.simplemovies.database.MovieDb


//region Wrappers

data class MoviesWrapper(
    val results: List<MovieNetwork>
)


data class GenresWrapper(
    val genres: List<GenreNetwork>
)


data class Cast(
    val cast: List<CastMember>
)
//endregion

data class MovieNetwork(
    val id: Int,
    val overview: String,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val original_name: String?,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<GenreNetwork>?,
    val runtime: Int?

)

data class MovieResult(
    val id: Int,
    val backdrop_path: String?,
    val genres: List<GenreNetwork>,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double,
    val runtime: Int?,
    val tagline: String?,

    //TO BE EXTRACTED
    val name: String?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?

)

data class GenreNetwork(
    val id: Int,
    val name: String
)

data class CastMember(
    val id: Int,
    val character: String,
    val gender: Int?,
    val name: String,
    val profile_path: String?
)

data class CrewMemeber(
    val credit_id: String,
    val name: String,
    val profile_path: String?
)

//region helper methods

fun List<MovieNetwork>.asMovieDatabase(): List<MovieDb> {
    return map {
        MovieDb(
            id = it.id,
            overview = it.overview,
            poster_path = it.poster_path ?: "",
            release_date = it.release_date?: "",
            title = it.title?: "",
            vote_average = it.vote_average,
            vote_count = it.vote_count,
            runtime = it.runtime
        )
    }
}

fun List<GenreNetwork>.asGenreDatabase(): List<GenreDb> {
    return map {
        GenreDb(
            id = it.id,
            name = it.name
        )
    }
}
//endregion
