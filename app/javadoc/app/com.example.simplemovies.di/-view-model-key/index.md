[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ViewModelKey](./index.md)

# ViewModelKey

`@Target([AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER]) @MapKey annotation class ViewModelKey`

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

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ViewModelKey(value: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<out ViewModel>)`<br>In order to create the map (inside genericviewmodel) we create an annotation class that will set the key as a KClass of our viewmodel |

### Properties

| Name | Summary |
|---|---|
| [value](value.md) | `val value: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<out ViewModel>`<br>KClass that extends viewmodel |
