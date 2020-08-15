package com.example.simplemovies.experimental.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.experimental.ExperimentalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * A dagger modules provides information on how to construct components needed in the component
 * or dependency graph.
 * */
@Module
abstract class ExperimentalModule {

    /**
     * Puts the viewmodel and its key inside the map of genericViewmodelFactory
     * */
    @Binds
    @IntoMap
    @ViewModelKey(ExperimentalViewModel::class)
    abstract fun bindViewmodel(viewmodel: ExperimentalViewModel): ViewModel
}