package com.example.simplemovies.experimental.di

import com.example.simplemovies.experimental.ExperimentalFragment
import dagger.Subcomponent

@Subcomponent(modules = [ExperimentalModule::class])
interface ExperimentalComponent {

    /**
     * A factory for a component.
     * A factory is a type with a single method that returns a new component instance each time it is called.
     * The parameters of that method allow the caller to provide the modules, dependencies
     * and bound instances required by the component.
     * */
    @Subcomponent.Factory
    interface Factory {
        fun create(): ExperimentalComponent
    }

    /**
     * inject the fragment
     * */
    fun inject(fragment: ExperimentalFragment)
}
