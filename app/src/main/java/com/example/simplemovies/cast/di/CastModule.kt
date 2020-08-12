package com.example.simplemovies.cast.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.cast.CastViewmodel
import com.example.simplemovies.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CastModule {

    /**
     * Puts the viewmodel and its key inside the map of genericViewmodelFactory
     * */
    @Binds
    @IntoMap
    @ViewModelKey(CastViewmodel::class)
    abstract fun bindViewmodel(viewmodel: CastViewmodel): ViewModel
}