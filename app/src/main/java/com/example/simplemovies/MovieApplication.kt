package com.example.simplemovies

import android.app.Application
import com.example.simplemovies.di.ApplicationGraph
import com.example.simplemovies.di.DaggerApplicationGraph

class MovieApplication : Application(){
    val graph: ApplicationGraph by lazy {
        initGraph()
    }

    open fun initGraph(): ApplicationGraph {
        return DaggerApplicationGraph.factory().create(applicationContext)
    }
}