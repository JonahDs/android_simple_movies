package com.example.simplemovies.moviepicker.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.moviepicker.MoviePickerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviePickerModule {
    //Sets the map with the provided map key
    @Binds
    @IntoMap
    @ViewModelKey(MoviePickerViewModel::class)
    abstract fun bindViewModel(viewmodel: MoviePickerViewModel): ViewModel
}