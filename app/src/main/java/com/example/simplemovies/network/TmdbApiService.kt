package com.example.simplemovies.network

import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMoviesWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PopularMoviesWrapper

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): MovieResult

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int
    ): Cast

    @GET("/3/genre/movie/list")
    suspend fun getAllMovieGenres(@Query("api_key") key: String): GenresWrapper
}

enum class APIStatus { LOADING, ERROR, DONE }
