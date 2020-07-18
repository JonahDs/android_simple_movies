package com.example.simplemovies.search.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.search.SearchLandingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchLandingModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchLandingViewModel::class)
    abstract fun bindViewModel(viewmodel: SearchLandingViewModel): ViewModel
}