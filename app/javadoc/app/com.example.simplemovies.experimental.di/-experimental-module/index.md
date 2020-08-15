[app](../../index.md) / [com.example.simplemovies.experimental.di](../index.md) / [ExperimentalModule](./index.md)

# ExperimentalModule

`@Module abstract class ExperimentalModule`

A dagger modules provides information on how to construct components needed in the component
or dependency graph.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExperimentalModule()`<br>A dagger modules provides information on how to construct components needed in the component or dependency graph. |

### Functions

| Name | Summary |
|---|---|
| [bindViewmodel](bind-viewmodel.md) | `abstract fun bindViewmodel(viewmodel: `[`ExperimentalViewModel`](../../com.example.simplemovies.experimental/-experimental-view-model/index.md)`): ViewModel`<br>Puts the viewmodel and its key inside the map of genericViewmodelFactory |
