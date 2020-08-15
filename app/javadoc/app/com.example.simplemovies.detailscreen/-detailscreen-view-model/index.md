[app](../../index.md) / [com.example.simplemovies.detailscreen](../index.md) / [DetailscreenViewModel](./index.md)

# DetailscreenViewModel

`class DetailscreenViewModel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DetailscreenViewModel(movieRepo: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [movieCast](movie-cast.md) | `val movieCast: LiveData<`[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)`>` |
| [navSelected](nav-selected.md) | `val navSelected: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [result](result.md) | `val result: LiveData<`[`MovieResult`](../../com.example.simplemovies.domain/-movie-result/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [displayCastDetails](display-cast-details.md) | `fun displayCastDetails(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the navigation property |
| [displayCastDetailsCompleted](display-cast-details-completed.md) | `fun displayCastDetailsCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Clears the navigation property |
| [setState](set-state.md) | `fun setState(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the  only if parameters are not equal or untouched (null, ""). This check prevents a configuration change to call the repository (and possibly the API) again while in no usecase this should happen. |
