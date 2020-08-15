[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [TmdbApiService](index.md) / [getRandomMovies](./get-random-movies.md)

# getRandomMovies

`@GET("3/discover/movie") abstract suspend fun getRandomMovies(@Query("vote_average.gte") seed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, @Query("page") pager: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10, @Query("include_adult") adult: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)

Get 200 movies with a score &gt;= 0 to then pick a random one

