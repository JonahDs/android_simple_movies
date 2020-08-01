package com.example.simplemovies.mockdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.simplemovies.domain.MoviesWrapper
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.network.Resource
import com.google.gson.Gson
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object MockedService {
    fun getMovies(): LiveData<Resource<MoviesWrapper>> = liveData {
        val res = Gson().fromJson(jsonMovie, MoviesWrapper::class.java)
        val resource = Resource.Success<MoviesWrapper>(res, APIStatus.DONE) as Resource<MoviesWrapper>
        emit(resource)
    }

    fun getFailedMovies(): LiveData<Resource<MoviesWrapper>> = liveData {
        val resource = Resource.Error<MoviesWrapper>(null, APIStatus.ERROR) as Resource<MoviesWrapper>
        emit(resource)
    }
}

//Livedata helper

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

    if(!latch.await(time, timeUnit)) {
        throw TimeoutException("Value never set")
    }

    return data as T
}