[app](../../index.md) / [com.example.simplemovies.moviepicker](../index.md) / [MoviePickerViewModel](./index.md)

# MoviePickerViewModel

`class MoviePickerViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MoviePickerViewModel(movieRepo: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [navigationProperty](navigation-property.md) | `val navigationProperty: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
| [randomMovie](random-movie.md) | `val randomMovie: LiveData<`[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [fetchRandomMovie](fetch-random-movie.md) | `fun fetchRandomMovie(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Subscribe to the repository call and catch it's values |
| [navigationCompleted](navigation-completed.md) | `fun navigationCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear navigation poperty |
