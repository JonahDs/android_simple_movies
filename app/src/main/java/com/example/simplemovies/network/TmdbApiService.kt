package com.example.simplemovies.network

import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieResult
import com.example.simplemovies.domain.PopularMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.themoviedb.org/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .build()

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

object TmdbApi {
    val retrofitService: TmdbApiService by lazy { retrofit.create(TmdbApiService::class.java) }
}


enum class APIStatus { LOADING, ERROR, DONE }
