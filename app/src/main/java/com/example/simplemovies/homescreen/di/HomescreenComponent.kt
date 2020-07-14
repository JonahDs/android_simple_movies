package com.example.simplemovies.homescreen.di

import com.example.simplemovies.homescreen.HomescreenFragment
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [HomesceenModule::class])
interface HomescreenComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomescreenComponent
    }

    fun inject(fragment: HomescreenFragment)
}