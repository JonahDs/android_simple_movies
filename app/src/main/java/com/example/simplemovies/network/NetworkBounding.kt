package com.example.simplemovies.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBounding<ResultType, RequestType> {

    private val results =
        MediatorLiveData<Resource<ResultType>>()

    init {
        results.value = Resource.Loading(null, APIStatus.LOADING)
        val source = fetchFromDb()
        results.addSource(source) { data ->
            Log.i("NETWORK_", data.toString())
            results.removeSource(source)
            if (shouldFetch(data)) {
                fetchFromNetwork(source)
            } else {
                results.addSource(source) { data ->
                    setValue(
                        Resource.Success(
                            data,
                            APIStatus.DONE
                        )
                    )
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (results.value != newValue) {
            results.value = newValue
        }
    }

    private fun fetchFromNetwork(source: LiveData<ResultType>) {
        val response = makeApiCall()
        results.addSource(source) { data ->
            setValue(Resource.Loading(data, APIStatus.LOADING))
        }
        results.addSource(response) { data ->
            results.removeSource(source)
            results.removeSource(response)
            when (data) {
                null -> setValue(Resource.Error(data, APIStatus.ERROR))
                is RequestType -> {
                    saveApiResToDb(data)
                    results.addSource(fetchFromDb()) {
                        setValue(Resource.Success(it, APIStatus.DONE))
                    }
                }
            }
        }
    }

    protected abstract fun saveApiResToDb(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun fetchFromDb(): LiveData<ResultType>

    protected abstract fun makeApiCall(): LiveData<RequestType>

    fun asLiveData(): LiveData<Resource<ResultType>> = results
}

sealed class Resource<T>(val data: T? = null, val status: APIStatus? = null) {
    class Success<T>(data: T, status: APIStatus) : Resource<T>(data, status)
    class Loading<T>(data: T?, status: APIStatus) : Resource<T>(data, status)
    class Error<T>(data: T? = null, status: APIStatus) : Resource<T>(data, status)
}