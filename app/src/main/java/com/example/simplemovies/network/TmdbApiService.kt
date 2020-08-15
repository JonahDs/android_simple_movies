package com.example.simplemovies.network

import com.example.simplemovies.domain.CastWrapper
import com.example.simplemovies.domain.GenresWrapper
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.MoviesWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    /**
     * Get popular movies of today
     * */
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MoviesWrapper

    /**
     * Get details of type and ID
     * */
    @GET("/3/{type}/{movie_id}")
    suspend fun getMovieDetails(
        @Path("type") type: String = "movies",
        @Path("movie_id") movie_id: Int
    ): MovieResult

    /**
     * Get the cast and crew of type and ID
     * */
    @GET("/3/{type}/{id}/credits")
    suspend fun getMovieCredits(
        @Path("type") type: String,
        @Path("id") id: Int
    ): CastWrapper

    /**
     * Get all existing genres
     * */
    @GET("/3/genre/movie/list")
    suspend fun getAllMovieGenres(): GenresWrapper

    /**
     * Get 200 movies with a score >= 0 to then pick a random one
     * */
    @GET("3/discover/movie")
    suspend fun getRandomMovies(
        @Query("vote_average.gte") seed: Int = 0,
        @Query("page") pager: Int = 10,
        @Query("include_adult") adult: Boolean = false
    ): MoviesWrapper

    /**
     * Get movies based on a search query
     * */
    @GET("3/search/movie")
    suspend fun getMoviesOfQuery(@Query("query") query: String): MoviesWrapper

    /**
     * Get movies based on genres, type and vote averages
     * */
    @GET("3/discover/{type}")
    suspend fun getDiscover(
        @Path("type") type: String? = "movie",
        @Query("with_genres") genreInc: List<String>? = listOf(),
        @Query("without_genres") genreExcl: List<String>? = listOf(),
        @Query("vote_average.gte") score: Int? = 0,
        @Query("vote_average.lte") cielScore: Int? = 0
    ): MoviesWrapper
}

/**
 * enumeration for the API states
 * */
enum class APIStatus { LOADING, ERROR, INTERMEDIATE, DONE }
