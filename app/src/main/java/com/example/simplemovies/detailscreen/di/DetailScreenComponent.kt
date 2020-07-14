package com.example.simplemovies.detailscreen.di

import com.example.simplemovies.detailscreen.DetailscreenFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailScreenModule::class])
interface DetailScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create():DetailScreenComponent
    }

    fun inject(fragment: DetailscreenFragment)
}