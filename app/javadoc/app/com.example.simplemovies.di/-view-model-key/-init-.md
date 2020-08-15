[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ViewModelKey](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ViewModelKey(value: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<out ViewModel>)`

In order to create the map (inside genericviewmodel) we create an annotation class that will
set the key as a KClass of our viewmodel

### Parameters

`value` - KClass that extends viewmodel

**Target**
only specified targets can use the annotation

**Retention**
Annotation is stored in binary output and visible for reflection

**MapKey**
multibinding

