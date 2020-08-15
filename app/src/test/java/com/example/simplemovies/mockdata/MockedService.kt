package com.example.simplemovies.mockdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.simplemovies.domain.CastWrapper
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.Resource
import com.example.simplemovies.network.SimpleBounding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Mocked calls that use Gson instead of moshi since Gson makes it a little easier
 * to convert string to an object
 * */
object MockedService {
    fun getMoviesSuccess(): Flow<Resource<MoviesWrapper>> {
        return object : SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall(): MoviesWrapper = withContext(IO) {
                Gson().fromJson(jsonMovie, MoviesWrapper::class.java)
            }
        }.asFlow().flowOn(IO)
    }

    fun getMoviesFailure(): Flow<Resource<MoviesWrapper>> {
        return object : SimpleBounding<MoviesWrapper>() {
            override suspend fun makeApiCall(): MoviesWrapper = withContext(IO) {
                throw IllegalArgumentException()
            }
        }.asFlow().flowOn(IO)
    }

    fun getCastSuccess(): Flow<Resource<CastWrapper>> {
        return object : SimpleBounding<CastWrapper>() {
            override suspend fun makeApiCall(): CastWrapper = withContext(IO) {
                Gson().fromJson(jsonCast, CastWrapper::class.java)
            }
        }.asFlow().flowOn(IO)
    }
}

/**
 * Allow the observation of livedata 'without' a lifecycleowner, this is a simple extension function
 * that returns the value of livedata
 * */
fun <T> LiveData<T>.getOrAwait(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwait.removeObserver(this)
        }
    }
    observeForever(observer)

    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("Value never set")
    }

    return data as T
}
