package com.example.simplemovies.search.di

import com.example.simplemovies.search.SearchLanding
import dagger.Subcomponent

@Subcomponent(modules = [SearchLandingModule::class])
interface SearchLandingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchLandingComponent
    }

    fun inject(fragment: SearchLanding)
}