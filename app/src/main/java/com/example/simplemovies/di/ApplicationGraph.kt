package com.example.simplemovies.di

import android.content.Context
import com.example.simplemovies.detailscreen.di.DetailScreenComponent
import com.example.simplemovies.homescreen.di.HomescreenComponent
import com.example.simplemovies.moviepicker.di.MoviePickerComponent
import com.example.simplemovies.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelBuilderModule::class, SubComponentModule::class, ApplicationModule::class, NetworkModule::class])
interface ApplicationGraph {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationGraph
    }

    fun homescreenComponent(): HomescreenComponent.Factory
    fun detailscreenComponent(): DetailScreenComponent.Factory
    fun moviepickerComponent(): MoviePickerComponent.Factory
}

@Module(subcomponents = [HomescreenComponent::class, DetailScreenComponent::class, MoviePickerComponent::class])
object SubComponentModule