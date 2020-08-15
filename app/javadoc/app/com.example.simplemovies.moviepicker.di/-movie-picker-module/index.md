[app](../../index.md) / [com.example.simplemovies.moviepicker.di](../index.md) / [MoviePickerModule](./index.md)

# MoviePickerModule

`@Module abstract class MoviePickerModule`

A dagger modules provides information on how to construct components needed in the component
or dependency graph.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MoviePickerModule()`<br>A dagger modules provides information on how to construct components needed in the component or dependency graph. |

### Functions

| Name | Summary |
|---|---|
| [bindViewModel](bind-view-model.md) | `abstract fun bindViewModel(viewmodel: `[`MoviePickerViewModel`](../../com.example.simplemovies.moviepicker/-movie-picker-view-model/index.md)`): ViewModel`<br>Puts the viewmodel and its key inside the map of genericViewmodelFactory |
