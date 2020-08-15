package com.example.simplemovies.utils

import android.os.SystemClock
import android.util.ArrayMap
import java.util.concurrent.TimeUnit

/**
 * Class that holds a map and returns true or false if the timer is over
 *
 * Used to help decide if data should get refreshed, each repository method implementing
 * NetworkBounding sets a key and timer
 * */
class DataManager {
    private val recordedMoments = ArrayMap<String, Long>()
    private var timeout: Long = 0L

    fun declareTimeout(timeout: Int, timeUnit: TimeUnit) {
        this.timeout = timeUnit.toMillis(timeout.toLong())
    }

    @Synchronized
    fun shouldRefresh(key: String): Boolean {
        val lastFetched = recordedMoments[key]
        val now = SystemClock.uptimeMillis()
        if (lastFetched == null) {
            recordedMoments[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            recordedMoments[key] = now
            return true
        }
        return false
    }
}
