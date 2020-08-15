[app](../../index.md) / [com.example.simplemovies.cast](../index.md) / [CastViewmodel](./index.md)

# CastViewmodel

`class CastViewmodel : ViewModel`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CastViewmodel(movierepo: `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [apiStatus](api-status.md) | `val apiStatus: LiveData<`[`APIStatus`](../../com.example.simplemovies.network/-a-p-i-status/index.md)`>` |
| [cast](cast.md) | `val cast: LiveData<`[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [setMovieId](set-movie-id.md) | `fun setMovieId(movieId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the  only if parameters are not equal or untouched (null, ""). This check prevents a configuration change to call the repository (and possibly the API) again while in no usecase this should happen. |
