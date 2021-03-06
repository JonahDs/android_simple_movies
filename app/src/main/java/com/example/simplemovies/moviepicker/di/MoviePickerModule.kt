package com.example.simplemovies.moviepicker.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.moviepicker.MoviePickerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * A dagger modules provides information on how to construct components needed in the component
 * or dependency graph.
 * */
@Module
abstract class MoviePickerModule {

    /**
     * Puts the viewmodel and its key inside the map of genericViewmodelFactory
     * */
    @Binds
    @IntoMap
    @ViewModelKey(MoviePickerViewModel::class)
    abstract fun bindViewModel(viewmodel: MoviePickerViewModel): ViewModel
}
