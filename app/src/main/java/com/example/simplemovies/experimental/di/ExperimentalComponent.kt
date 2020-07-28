package com.example.simplemovies.experimental.di

import androidx.fragment.app.Fragment
import com.example.simplemovies.experimental.Experimental
import dagger.Subcomponent

@Subcomponent(modules = [ExperimentalModule::class])
interface ExperimentalComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExperimentalComponent
    }

    fun inject(fragment: Experimental)
}