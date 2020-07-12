package com.example.simplemovies.utils

import android.os.SystemClock
import android.util.ArrayMap
import java.util.concurrent.TimeUnit

class DataManager(timeout: Int, timeUnit: TimeUnit) {
    private val recordedMoments = ArrayMap<String, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    fun shouldRefresh(key: String): Boolean {
        val lastFetched = recordedMoments[key]
        val now = SystemClock.uptimeMillis()
        if(lastFetched == null) {
            recordedMoments[key] = now
            return true
        }
        if(now - lastFetched > timeout) {
            recordedMoments[key] = now
            return true
        }
        return false
    }
}