package com.example.simplemovies.network

import com.example.simplemovies.utils.DataManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/"

@Module
class NetworkModule {

    @Provides
    fun provideService(retrofit: Retrofit): TmdbApiService {
        return retrofit.create(TmdbApiService::class.java)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient).build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
            val url = it.request().url().newBuilder().addQueryParameter("api_key", "eebddf3c28edf2691214c6ece5688e32").build()
            request.url(url)
            return@addInterceptor it.proceed(request.build())
        }.build()
    }

    @Provides
    @Singleton
    fun provideDataManager(): DataManager {
        return DataManager()
    }
}