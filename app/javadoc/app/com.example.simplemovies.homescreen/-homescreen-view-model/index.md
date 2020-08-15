[app](../../index.md) / [com.example.simplemovies.homescreen](../index.md) / [HomescreenViewModel](./index.md)

# HomescreenViewModel

`class HomescreenViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HomescreenViewModel(movieRepo: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [displayableMovies](displayable-movies.md) | `val displayableMovies: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`>>` |
| [navSelected](nav-selected.md) | `val navSelected: LiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [clearMovies](clear-movies.md) | `fun clearMovies(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [displayMovieCompleted](display-movie-completed.md) | `fun displayMovieCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear navigation |
| [displayMovieDetails](display-movie-details.md) | `fun displayMovieDetails(movieId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the navigation property with the clicked id |
| [fetchMovies](fetch-movies.md) | `fun fetchMovies(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Subscribe to the repository call and catch it's values |
| [manageMovieResource](manage-movie-resource.md) | `fun manageMovieResource(resources: `[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the API status and data only if not null |
