package com.example.simplemovies.repositories

import com.example.simplemovies.domain.PopularMovies
import com.example.simplemovies.network.TmdbApi

class MovieRepository {

    suspend fun getMovies(): PopularMovies {
        return TmdbApi.retrofitService.getPopularMovies("eebddf3c28edf2691214c6ece5688e32")
    }

}