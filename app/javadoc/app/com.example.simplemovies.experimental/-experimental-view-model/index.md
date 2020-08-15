[app](../../index.md) / [com.example.simplemovies.experimental](../index.md) / [ExperimentalViewModel](./index.md)

# ExperimentalViewModel

`class ExperimentalViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ExperimentalViewModel(movieRepository: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`, genreRepository: `[`GenreRepository`](../../com.example.simplemovies.repositories/-genre-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [discover](discover.md) | `val discover: LiveData<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>` |
| [genres](genres.md) | `val genres: LiveData<`[`GenresWrapper`](../../com.example.simplemovies.domain/-genres-wrapper/index.md)`>` |
| [navProperty](nav-property.md) | `val navProperty: LiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [fetchDiscover](fetch-discover.md) | `fun fetchDiscover(state: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, score: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, resource: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Subscribe to the repository call and catch it's values. |
| [manageChips](manage-chips.md) | `fun manageChips(genre: `[`GenreNetwork`](../../com.example.simplemovies.domain/-genre-network/index.md)`, isChecked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Manage the chips, if checked is true then add it, else remove it |
| [navCompleted](nav-completed.md) | `fun navCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the navigation property |
| [navSelected](nav-selected.md) | `fun navSelected(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set navigation property to the clicked id |
