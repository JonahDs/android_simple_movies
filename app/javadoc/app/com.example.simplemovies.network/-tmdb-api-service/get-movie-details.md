[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [TmdbApiService](index.md) / [getMovieDetails](./get-movie-details.md)

# getMovieDetails

`@GET("/3/{type}/{movie_id}") abstract suspend fun getMovieDetails(@Path("type") type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "movies", @Path("movie_id") movie_id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`MovieResult`](../../com.example.simplemovies.domain/-movie-result/index.md)

Get details of type and ID

