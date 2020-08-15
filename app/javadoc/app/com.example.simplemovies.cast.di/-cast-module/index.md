[app](../../index.md) / [com.example.simplemovies.cast.di](../index.md) / [CastModule](./index.md)

# CastModule

`@Module abstract class CastModule`

A dagger modules provides information on how to construct components needed in the component
or dependency graph.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CastModule()`<br>A dagger modules provides information on how to construct components needed in the component or dependency graph. |

### Functions

| Name | Summary |
|---|---|
| [bindViewmodel](bind-viewmodel.md) | `abstract fun bindViewmodel(viewmodel: `[`CastViewmodel`](../../com.example.simplemovies.cast/-cast-viewmodel/index.md)`): ViewModel`<br>Puts the viewmodel and its key inside the map of genericViewmodelFactory |
