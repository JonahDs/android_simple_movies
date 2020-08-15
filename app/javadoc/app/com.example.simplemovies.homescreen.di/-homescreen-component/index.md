[app](../../index.md) / [com.example.simplemovies.homescreen.di](../index.md) / [HomescreenComponent](./index.md)

# HomescreenComponent

`@Subcomponent([HomesceenModule]) interface HomescreenComponent`

### Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | `interface Factory`<br>A factory for a component. A factory is a type with a single method that returns a new component instance each time it is called. The parameters of that method allow the caller to provide the modules, dependencies and bound instances required by the component. |

### Functions

| Name | Summary |
|---|---|
| [inject](inject.md) | `abstract fun inject(fragment: `[`HomescreenFragment`](../../com.example.simplemovies.homescreen/-homescreen-fragment/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>inject the fragment |
