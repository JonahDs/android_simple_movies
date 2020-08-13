package com.example.simplemovies.moviepicker.di

import com.example.simplemovies.moviepicker.MoviePickerFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviePickerModule::class])
interface MoviePickerComponent {

    /**
     * A factory for a component.
     * A factory is a type with a single method that returns a new component instance each time it is called.
     * The parameters of that method allow the caller to provide the modules, dependencies
     * and bound instances required by the component.
     * */
    @Subcomponent.Factory
    interface Factory {
        fun create(): MoviePickerComponent
    }

    /**
     * inject the fragment
     * */
    fun inject(fragment: MoviePickerFragment)
}