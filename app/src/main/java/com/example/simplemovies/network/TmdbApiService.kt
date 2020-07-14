package com.example.simplemovies.network

import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") key: String): PopularMovies

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): MovieResult

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): Cast
}

enum class APIStatus { LOADING, ERROR, DONE }
