[app](../../index.md) / [com.example.simplemovies.cast](../index.md) / [CastViewmodel](index.md) / [setMovieId](./set-movie-id.md)

# setMovieId

`fun setMovieId(movieId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set the  only if parameters are not equal or untouched (null, "").
This check prevents a configuration change to call the repository (and possibly the API)
again while in no usecase this should happen.

### Parameters

`movieId` - the movie or tv id

`type` - fetch type: movie, tv