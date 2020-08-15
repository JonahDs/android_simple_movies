[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [TmdbApiService](./index.md)

# TmdbApiService

`interface TmdbApiService`

### Functions

| Name | Summary |
|---|---|
| [getAllMovieGenres](get-all-movie-genres.md) | `abstract suspend fun getAllMovieGenres(): `[`GenresWrapper`](../../com.example.simplemovies.domain/-genres-wrapper/index.md)<br>Get all existing genres |
| [getDiscover](get-discover.md) | `abstract suspend fun getDiscover(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "movie", genreInc: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>? = listOf(), genreExcl: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>? = listOf(), score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = 0, cielScore: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = 0): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)<br>Get movies based on genres, type and vote averages |
| [getMovieCredits](get-movie-credits.md) | `abstract suspend fun getMovieCredits(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)<br>Get the cast and crew of type and ID |
| [getMovieDetails](get-movie-details.md) | `abstract suspend fun getMovieDetails(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "movies", movie_id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`MovieResult`](../../com.example.simplemovies.domain/-movie-result/index.md)<br>Get details of type and ID |
| [getMoviesOfQuery](get-movies-of-query.md) | `abstract suspend fun getMoviesOfQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)<br>Get movies based on a search query |
| [getPopularMovies](get-popular-movies.md) | `abstract suspend fun getPopularMovies(): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)<br>Get popular movies of today |
| [getRandomMovies](get-random-movies.md) | `abstract suspend fun getRandomMovies(seed: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, pager: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10, adult: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)<br>Get 200 movies with a score &gt;= 0 to then pick a random one |
