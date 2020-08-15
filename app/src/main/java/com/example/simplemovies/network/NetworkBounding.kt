package com.example.simplemovies.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * SimpleBounding is an object that handles and emits the correct values for the repositories.
 * This way the viewmodel can get rid of a bunch of boilerplate code like
 *
 * scope.launch {
 *  try {
 *      api = loading
 *      res = repo.call()
 *      api = done
 *  } catch()
 *  {
 *      api = error
 *  }
 * }
 *
 * The viewmodels will only need to observe the repositories and handle incoming events.
 *
 * SimpleBounding only has one overridable method: makeApiCall() since no database interaction
 * will happen (repo's implementing simplebounding will ALWAYS fetch from the internet).
 *
 * flows:
 * emit loading -> emit done with data (internet is available)
 * emit loading -> emit error (internet not available)
 *
 * */
abstract class SimpleBounding<T> {

    private val flow = flow<Resource<T>> {
        // Emit LOADING from the start
        emit(Resource.Loading(null, APIStatus.LOADING))
        try {

            // Make the API call
            val data = makeApiCall()

            // If success emit DONE with the data
            emit(Resource.Success(data, APIStatus.DONE))
        } catch (e: Exception) {

            // If the call failed emit ERROR
            emit(Resource.Error(null, APIStatus.ERROR))
        }
    }

    protected abstract suspend fun makeApiCall(): T

    fun asFlow() = flow
}

/**
 * Networkbounding is an 'advanced' version of SimpleBounding. Motivation behind this is the same tho.
 *
 * repo's implementing this method should have database interactions, otherwise use SimpleBounding
 *
 * NetworkBounding has four overridable methods
 * saveApiResToDb -> save incoming api call data to the database
 * shouldFetch -> needs the data inside the database an update?
 * fetchFromDb -> get the data from the database
 * mapApiCall -> call the internet
 *
 * */
abstract class NetworkBounding<T> {

    private val flow = flow<Resource<T>> {
        // Get the data from database
        val local = fetchFromDb().first()

        // Emit LOADING to repo
        emit(Resource.Loading(null, APIStatus.LOADING))
        if (shouldFetch(local)) {
            try {
                // Make the API call
                val data = makeApiCall()

                // Emit INTERMEDIATE to update the UI text (indicating to the user that something is happening)
                emit(Resource.Loading(null, APIStatus.INTERMEDIATE))

                // Save the data to the database
                saveApiResToDb(data)

                // Collect the data from database emit it with DONE
                fetchFromDb().collect { dbRes ->
                    emit(Resource.Success(dbRes, APIStatus.DONE))
                }
            } catch (e: Exception) {
                // Something went wrong, try to see if there is data inside the database
                val buffered = fetchFromDb().first()

                // If there is data emit it with DONE else emit ERROR
                if (buffered != null) {
                    fetchFromDb().collect { dbRes ->
                        emit(Resource.Success(dbRes, APIStatus.DONE))
                    }
                } else {
                    emit(Resource.Error(null, APIStatus.ERROR))
                }
            }
        } else {
            // The data does'nt need to be refreshed so emit database result and DONE
            emit(Resource.Success(local, APIStatus.DONE))
        }
    }

    protected abstract fun saveApiResToDb(item: T)

    protected abstract fun shouldFetch(data: T?): Boolean

    protected abstract fun fetchFromDb(): Flow<T?>

    protected abstract suspend fun makeApiCall(): T

    fun asFlow() = flow
}

/**
 * Class to wrap our data in together with an API status, this way our viewmodels can easily
 * set everything
 * */
sealed class Resource<T>(val data: T? = null, val status: APIStatus? = null) {
    class Success<T>(data: T?, status: APIStatus) : Resource<T>(data, status)
    class Loading<T>(data: T?, status: APIStatus) : Resource<T>(data, status)
    class Error<T>(data: T? = null, status: APIStatus) : Resource<T>(data, status)
}
