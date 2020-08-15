[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ApplicationGraph](./index.md)

# ApplicationGraph

`@Singleton @Component([ViewModelBuilderModule, SubComponentModule, ApplicationModule, NetworkModule]) interface ApplicationGraph`

Dagger component

### Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | `interface Factory`<br>A factory for a component. A factory is a type with a single method that returns a new component instance each time it is called. The parameters of that method allow the caller to provide the modules, dependencies and bound instances required by the component. |

### Functions

| Name | Summary |
|---|---|
| [castComponent](cast-component.md) | `abstract fun castComponent(): `[`Factory`](../../com.example.simplemovies.cast.di/-cast-component/-factory/index.md) |
| [detailscreenComponent](detailscreen-component.md) | `abstract fun detailscreenComponent(): `[`Factory`](../../com.example.simplemovies.detailscreen.di/-detail-screen-component/-factory/index.md) |
| [experimentalComponent](experimental-component.md) | `abstract fun experimentalComponent(): `[`Factory`](../../com.example.simplemovies.experimental.di/-experimental-component/-factory/index.md) |
| [homescreenComponent](homescreen-component.md) | `abstract fun homescreenComponent(): `[`Factory`](../../com.example.simplemovies.homescreen.di/-homescreen-component/-factory/index.md) |
| [moviepickerComponent](moviepicker-component.md) | `abstract fun moviepickerComponent(): `[`Factory`](../../com.example.simplemovies.moviepicker.di/-movie-picker-component/-factory/index.md) |
| [searchscreenComponent](searchscreen-component.md) | `abstract fun searchscreenComponent(): `[`Factory`](../../com.example.simplemovies.search.di/-search-landing-component/-factory/index.md) |
