package com.example.simplemovies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Generic viewmodel factory
 * Dagger 2 allows multibinding, this makes it possible create a map of objects
 * with key a class that extends ViewModel and value the actual instance of the custom viewmodel
 *
 * On compile time Dagger creates the map and passes it to the viewmodelFactory as an argument.
 * In the override function we simply pick the correct instance from inside the map.
 *
 * @JvmSuppressWildcards to properly compile
 * */
@Suppress("UNCHECKED_CAST")
class GenericViewModelFactory @Inject constructor(private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Can/'t model this class")
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

/**
 * Module to provide a viewmodel factory, note the returntype
 * */
@Module
abstract class ViewModelBuilderModule {
    // Return a factory based on our own implemented code
    @Binds
    internal abstract fun bindViewModelFactory(factory: GenericViewModelFactory): ViewModelProvider.Factory
}

/**
 * In order to create the map (inside genericviewmodel) we create an annotation class that will
 * set the key as a KClass of our viewmodel
 * @Target only specified targets can use the annotation
 * @Retention Annotation is stored in binary output and visible for reflection
 * @MapKey multibinding
 *
 * @param value KClass that extends viewmodel
 * */
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
