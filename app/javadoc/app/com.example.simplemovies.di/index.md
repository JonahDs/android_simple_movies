[app](../index.md) / [com.example.simplemovies.di](./index.md)

## Package com.example.simplemovies.di

### Types

| Name | Summary |
|---|---|
| [ApplicationGraph](-application-graph/index.md) | `interface ApplicationGraph`<br>Dagger component |
| [ApplicationModule](-application-module/index.md) | `object ApplicationModule` |
| [GenericViewModelFactory](-generic-view-model-factory/index.md) | `class GenericViewModelFactory : Factory`<br>Generic viewmodel factory Dagger 2 allows multibinding, this makes it possible create a map of objects with key a class that extends ViewModel and value the actual instance of the custom viewmodel |
| [SubComponentModule](-sub-component-module.md) | `object SubComponentModule`<br>Application subcomponents |
| [ViewModelBuilderModule](-view-model-builder-module/index.md) | `abstract class ViewModelBuilderModule`<br>Module to provide a viewmodel factory, note the returntype |

### Annotations

| Name | Summary |
|---|---|
| [ViewModelKey](-view-model-key/index.md) | `annotation class ViewModelKey`<br>In order to create the map (inside genericviewmodel) we create an annotation class that will set the key as a KClass of our viewmodel |
