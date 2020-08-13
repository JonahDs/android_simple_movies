package com.example.simplemovies.detailscreen.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.detailscreen.DetailscreenViewModel
import com.example.simplemovies.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailScreenModule {

    /**
     * Puts the viewmodel and its key inside the map of genericViewmodelFactory
     * */
    @Binds
    @IntoMap
    @ViewModelKey(DetailscreenViewModel::class)
    abstract fun bindViewmodel(viewmodel: DetailscreenViewModel): ViewModel
}