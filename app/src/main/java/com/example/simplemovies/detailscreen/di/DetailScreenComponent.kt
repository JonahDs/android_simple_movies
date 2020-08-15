package com.example.simplemovies.detailscreen.di

import com.example.simplemovies.detailscreen.DetailscreenFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailScreenModule::class])
interface DetailScreenComponent {

    /**
     * A factory for a component.
     * A factory is a type with a single method that returns a new component instance each time it is called.
     * The parameters of that method allow the caller to provide the modules, dependencies
     * and bound instances required by the component.
     * */
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailScreenComponent
    }

    /**
     * inject the fragment
     * */
    fun inject(fragment: DetailscreenFragment)
}
