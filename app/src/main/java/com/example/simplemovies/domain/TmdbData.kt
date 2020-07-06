package com.example.simplemovies.domain

data class PopularMovies(
    val results: List<Result>
)


data class Result(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

data class MovieResult(
    val id: Int,
    val backdrop_path: String,
    val genres: List<Genre>,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)

data class Genre(
    val id: Int,
    val name: String
)

data class Cast(
    val cast: List<CastMember>
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