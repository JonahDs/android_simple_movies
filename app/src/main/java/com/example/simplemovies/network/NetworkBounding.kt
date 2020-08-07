package com.example.simplemovies.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class SimpleBounding<T> {

    private val flow = flow<Resource<T>> {
        emit(Resource.Loading(null, APIStatus.LOADING))
        try {
            val data = makeApiCall()
            emit(Resource.Success(data, APIStatus.DONE))
        } catch (e: Exception) {
            emit(Resource.Error(null, APIStatus.ERROR))
        }
    }

    protected abstract suspend fun makeApiCall(): T

    fun asFlow() = flow
}

abstract class NetworkBoundingNew<T> {

    private val flow = flow<Resource<T>> {
        val local = fetchFromDb().first()

        emit(Resource.Loading(null, APIStatus.LOADING))
        if (shouldFetch(local)) {
            try {
                val data = makeApiCall()
                emit(Resource.Loading(null, APIStatus.INTERMEDIATE))
                saveApiResToDb(data)
                fetchFromDb().collect { dbRes ->
                    emit(Resource.Success(dbRes, APIStatus.DONE))
                }
            } catch (e: Exception) {
                val buffered = fetchFromDb().first()
                if (buffered != null) {
                    fetchFromDb().collect { dbRes ->
                        emit(Resource.Success(dbRes, APIStatus.DONE))
                    }
                } else {
                    emit(Resource.Error(null, APIStatus.ERROR))
                }
            }
        } else {
            emit(Resource.Success(local, APIStatus.DONE))
        }
    }


    protected abstract fun saveApiResToDb(item: T)

    protected abstract fun shouldFetch(data: T?): Boolean

    protected abstract fun fetchFromDb(): Flow<T>

    protected abstract suspend fun makeApiCall(): T

    fun asFlow() = flow

}


sealed class Resource<T>(val data: T? = null, val status: APIStatus? = null) {
    class Success<T>(data: T, status: APIStatus) : Resource<T>(data, status)
    class Loading<T>(data: T?, status: APIStatus) : Resource<T>(data, status)
    class Error<T>(data: T? = null, status: APIStatus) : Resource<T>(data, status)
}
