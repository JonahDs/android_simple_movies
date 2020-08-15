[app](../../index.md) / [com.example.simplemovies.homescreen.di](../index.md) / [HomesceenModule](./index.md)

# HomesceenModule

`@Module abstract class HomesceenModule`

A dagger modules provides information on how to construct components needed in the component
or dependency graph.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HomesceenModule()`<br>A dagger modules provides information on how to construct components needed in the component or dependency graph. |

### Functions

| Name | Summary |
|---|---|
| [bindViewModel](bind-view-model.md) | `abstract fun bindViewModel(viewmodel: `[`HomescreenViewModel`](../../com.example.simplemovies.homescreen/-homescreen-view-model/index.md)`): ViewModel`<br>Puts the viewmodel and its key inside the map of genericViewmodelFactory |
