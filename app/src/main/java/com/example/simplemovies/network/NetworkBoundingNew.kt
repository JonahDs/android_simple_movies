package com.example.simplemovies.network

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

abstract class NetworkBoundingNew<T> {

    private val flow = liveData<Resource<T>> {
        fetchFromDb().flowOn(IO).collect {
            emit(Resource.Loading(null, APIStatus.LOADING))
            if (shouldFetch(it)) {
                val data = makeApiCall()
                emit(Resource.Loading(data, APIStatus.INTERMEDIATE))
                when (data) {
                    null -> emit(Resource.Error(null, APIStatus.ERROR))
                    else -> {
                        saveApiResToDb(data)
                        fetchFromDb().collect { dbRes ->
                            emit(Resource.Success(dbRes, APIStatus.DONE))
                        }
                    }
                }
            } else {
                emit(Resource.Success(it, APIStatus.DONE))
            }
        }
    }


    protected abstract suspend fun saveApiResToDb(item: T)

    protected abstract fun shouldFetch(data: T?): Boolean

    protected abstract fun fetchFromDb(): Flow<T>

    protected abstract suspend fun makeApiCall(): T

    fun asLiveData() = flow

}