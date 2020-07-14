package com.example.simplemovies.homescreen.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.homescreen.HomescreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomesceenModule {
    //Sets the map with the provided map key
    @Binds
    @IntoMap
    @ViewModelKey(HomescreenViewModel::class)
    abstract fun bindViewModel(viewmodel: HomescreenViewModel): ViewModel
}