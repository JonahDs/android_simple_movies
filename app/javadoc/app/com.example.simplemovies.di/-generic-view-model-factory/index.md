[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [GenericViewModelFactory](./index.md)

# GenericViewModelFactory

`class GenericViewModelFactory : Factory`

Generic viewmodel factory
Dagger 2 allows multibinding, this makes it possible create a map of objects
with key a class that extends ViewModel and value the actual instance of the custom viewmodel

On compile time Dagger creates the map and passes it to the viewmodelFactory as an argument.
In the override function we simply pick the correct instance from inside the map.

**JvmSuppressWildcards**
to properly compile

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GenericViewModelFactory(creators: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out ViewModel>, Provider<ViewModel>>)`<br>Generic viewmodel factory Dagger 2 allows multibinding, this makes it possible create a map of objects with key a class that extends ViewModel and value the actual instance of the custom viewmodel |

### Functions

| Name | Summary |
|---|---|
| [create](create.md) | `fun <T : ViewModel?> create(modelClass: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<`[`T`](create.md#T)`>): `[`T`](create.md#T) |
