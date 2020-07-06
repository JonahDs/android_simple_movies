package com.example.simplemovies.repositories

import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMovies
import com.example.simplemovies.network.TmdbApi

class MovieRepository {

    private val API_KEY = "eebddf3c28edf2691214c6ece5688e32"

    suspend fun getMovies(): PopularMovies {
        return TmdbApi.retrofitService.getPopularMovies(API_KEY)
    }

    suspend fun getMovieDetails(movieId: Int): MovieResult {
        return TmdbApi.retrofitService.getMovieDetails(movieId, API_KEY)
    }

    suspend fun getMovieCast(movieId: Int): Cast {
        return TmdbApi.retrofitService.getMovieCredits(movieId, API_KEY)
    }

}