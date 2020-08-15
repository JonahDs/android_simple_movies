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

/**
 * A dagger modules provides information on how to construct components needed in the component
 * or dependency graph.
 * */
@Module
object NetworkModule {

    /**
     * Provides a TmdbApiService based on retrofit
     *
     * @param retrofit A retrofit instance
     * @return an instance of TmdbApiService
     * */
    @JvmStatic
    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): TmdbApiService {
        return retrofit.create(TmdbApiService::class.java)
    }

    /**
     * Provides a Moshi instance
     *
     * @return an instance of Moshi
     * */
    @JvmStatic
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    /**
     * Provides a Retrofit instance
     *
     * @param okHttpClient OkHttpClient instance
     * @param moshi Moshi instance
     * */
    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient).build()
    }

    /**
     * Provides a OkHttpClient instance
     *
     * @return instance of OkHttpClient
     * */
    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
            val url = it.request().url().newBuilder().addQueryParameter("api_key", "eebddf3c28edf2691214c6ece5688e32").build()
            request.url(url)
            return@addInterceptor it.proceed(request.build())
        }.build()
    }

    /**
     * Provides a Datamanager instance
     * */
    @JvmStatic
    @Provides
    @Singleton
    fun provideDataManager(): DataManager {
        return DataManager()
    }
}
