package com.example.simplemovies.network

import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MoviesWrapper

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): MovieResult

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int
    ): Cast

    @GET("/3/genre/movie/list")
    suspend fun getAllMovieGenres(): GenresWrapper

    @GET("3/discover/movie")
    suspend fun getMoviesWithGenre(
        @Query("with_genres") id: String,
        @Query("include_adult") adult: Boolean = false,
        @Query("include_video") vid: Boolean = false
    ): MoviesWrapper


    //Get 200 movies with a score >= 0 to then pick a random one
    @GET("3/discover/movie")
    suspend fun getRandomMovies(
        @Query("vote_average.gte") seed: Int = 0,
        @Query("page") pager: Int = 10,
        @Query("include_adult") adult: Boolean = false
    ): MoviesWrapper

    @GET("3/search/movie")
    suspend fun getMoviesOfQuery(@Query("query") query: String): MoviesWrapper

}

enum class APIStatus { LOADING, ERROR, DONE }
