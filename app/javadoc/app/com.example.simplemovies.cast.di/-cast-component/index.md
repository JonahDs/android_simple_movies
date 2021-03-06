[app](../../index.md) / [com.example.simplemovies.cast.di](../index.md) / [CastComponent](./index.md)

# CastComponent

`@Subcomponent([CastModule]) interface CastComponent`

Dagger component

### Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | `interface Factory`<br>A factory for a component. A factory is a type with a single method that returns a new component instance each time it is called. The parameters of that method allow the caller to provide the modules, dependencies and bound instances required by the component. |

### Functions

| Name | Summary |
|---|---|
| [inject](inject.md) | `abstract fun inject(fragment: `[`CastFragment`](../../com.example.simplemovies.cast/-cast-fragment/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>inject the fragment |
