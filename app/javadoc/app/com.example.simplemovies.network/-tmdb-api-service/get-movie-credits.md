[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [TmdbApiService](index.md) / [getMovieCredits](./get-movie-credits.md)

# getMovieCredits

`@GET("/3/{type}/{id}/credits") abstract suspend fun getMovieCredits(@Path("type") type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, @Path("id") id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)

Get the cast and crew of type and ID

