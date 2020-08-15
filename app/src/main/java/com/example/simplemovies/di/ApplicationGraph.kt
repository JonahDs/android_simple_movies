package com.example.simplemovies.di

import android.content.Context
import com.example.simplemovies.cast.di.CastComponent
import com.example.simplemovies.detailscreen.di.DetailScreenComponent
import com.example.simplemovies.experimental.di.ExperimentalComponent
import com.example.simplemovies.homescreen.di.HomescreenComponent
import com.example.simplemovies.moviepicker.di.MoviePickerComponent
import com.example.simplemovies.network.NetworkModule
import com.example.simplemovies.search.di.SearchLandingComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

/**
 * Dagger component
 * */
@Singleton
@Component(modules = [ViewModelBuilderModule::class, SubComponentModule::class, ApplicationModule::class, NetworkModule::class])
interface ApplicationGraph {

    /**
     * A factory for a component.
     * A factory is a type with a single method that returns a new component instance each time it is called.
     * The parameters of that method allow the caller to provide the modules, dependencies
     * and bound instances required by the component.
     * */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationGraph
    }

    // Subcomponents factories
    fun homescreenComponent(): HomescreenComponent.Factory
    fun detailscreenComponent(): DetailScreenComponent.Factory
    fun moviepickerComponent(): MoviePickerComponent.Factory
    fun searchscreenComponent(): SearchLandingComponent.Factory
    fun experimentalComponent(): ExperimentalComponent.Factory
    fun castComponent(): CastComponent.Factory
}

/**
 * Application subcomponents
 * */
@Module(subcomponents = [HomescreenComponent::class, DetailScreenComponent::class, MoviePickerComponent::class, SearchLandingComponent::class, ExperimentalComponent::class, CastComponent::class])
object SubComponentModule
