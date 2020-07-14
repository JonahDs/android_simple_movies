package com.example.simplemovies.moviepicker.di

import com.example.simplemovies.moviepicker.MoviePickerFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviePickerModule::class])
interface MoviePickerComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MoviePickerComponent
    }

    fun inject(fragment: MoviePickerFragment)
}