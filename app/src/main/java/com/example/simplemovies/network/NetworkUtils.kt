package com.example.simplemovies.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

fun <T> invokeCall(call: T): LiveData<Resource<T>> = liveData {
    emit(Resource.Loading(null, APIStatus.LOADING) as Resource<T>)
    emit(Resource.Success(call, APIStatus.DONE) as Resource<T>)
}

fun <T>invokeError(): LiveData<Resource<T>> = liveData {
    emit(Resource.Error(null, APIStatus.ERROR) as Resource<T>)
}