package com.example.simplemovies.cast.di

import com.example.simplemovies.cast.CastFragment
import dagger.Subcomponent

/**
 * Dagger component
 * */
@Subcomponent(modules = [CastModule::class])
interface CastComponent {

    /**
     * A factory for a component.
     * A factory is a type with a single method that returns a new component instance each time it is called.
     * The parameters of that method allow the caller to provide the modules, dependencies
     * and bound instances required by the component.
     * */
    @Subcomponent.Factory
    interface Factory {
        fun create(): CastComponent
    }

    /**
     * inject the fragment
     * */
    fun inject(fragment: CastFragment)
}
