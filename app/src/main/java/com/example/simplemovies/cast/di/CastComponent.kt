package com.example.simplemovies.cast.di

import com.example.simplemovies.cast.CastFragment
import dagger.Subcomponent

@Subcomponent(modules = [CastModule::class])
interface CastComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CastComponent
    }

    fun inject(fragment: CastFragment)

}