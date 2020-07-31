package com.example.simplemovies.experimental.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.experimental.ExperimentalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExperimentalModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExperimentalViewModel::class)
    abstract fun bindViewmodel(viewmodel: ExperimentalViewModel): ViewModel
}