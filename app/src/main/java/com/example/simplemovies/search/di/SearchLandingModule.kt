package com.example.simplemovies.search.di

import androidx.lifecycle.ViewModel
import com.example.simplemovies.di.ViewModelKey
import com.example.simplemovies.search.SearchLandingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * A dagger modules provides information on how to construct components needed in the component
 * or dependency graph.
 * */
@Module
abstract class SearchLandingModule {

    /**
     * Puts the viewmodel and its key inside the map of genericViewmodelFactory
     * */
    @Binds
    @IntoMap
    @ViewModelKey(SearchLandingViewModel::class)
    abstract fun bindViewModel(viewmodel: SearchLandingViewModel): ViewModel
}
