[app](../../index.md) / [com.example.simplemovies.search](../index.md) / [SearchLandingViewModel](./index.md)

# SearchLandingViewModel

`class SearchLandingViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SearchLandingViewModel(movierepo: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [navigation](navigation.md) | `val navigation: LiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [searchRes](search-res.md) | `val searchRes: LiveData<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [navigateToDetail](navigate-to-detail.md) | `fun navigateToDetail(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the navigation property to the clicked id |
| [navigationCompleted](navigation-completed.md) | `fun navigationCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clear the navigation |
| [setQuery](set-query.md) | `fun setQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the  only if parameters are not equal or untouched (null, ""). This check prevents a configuration change to call the repository (and possibly the API) again while in no usecase this should happen. |
