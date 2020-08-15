package com.example.simplemovies

import android.app.Application
import com.example.simplemovies.di.ApplicationGraph
import com.example.simplemovies.di.DaggerApplicationGraph

/**
 * Application that hosts a root scope component for dagger
 * */
class MovieApplication : Application() {
    val graph: ApplicationGraph by lazy {
        initGraph()
    }

    private fun initGraph(): ApplicationGraph {
        return DaggerApplicationGraph.factory().create(applicationContext)
    }
}
