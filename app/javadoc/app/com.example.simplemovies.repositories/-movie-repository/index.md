[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [MovieRepository](./index.md)

# MovieRepository

`class MovieRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MovieRepository(tmdbApi: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, movieDao: `[`MovieDao`](../../com.example.simplemovies.database/-movie-dao/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [getDiscover](get-discover.md) | `fun getDiscover(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "movie", genresInc: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, genresExl: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`<br>Example repository method that implements SimpleBounding and returns the flow so its subscribable from withing the viewmodel |
| [getMovieCast](get-movie-cast.md) | `fun getMovieCast(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)`>>`<br>Example repository method that implements SimpleBounding and returns the flow so its subscribable from withing the viewmodel |
| [getMovieDetails](get-movie-details.md) | `fun getMovieDetails(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MovieResult`](../../com.example.simplemovies.domain/-movie-result/index.md)`>>`<br>Example repository method that implements SimpleBounding and returns the flow so its subscribable from withing the viewmodel |
| [getMoviesOfFlow](get-movies-of-flow.md) | `fun getMoviesOfFlow(): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`<br>Example repository method that implements NetworkBounding and returns the flow so its subscribable from withing the viewmodel |
| [getMoviesOfQuery](get-movies-of-query.md) | `fun getMoviesOfQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`<br>Example repository method that implements SimpleBounding and returns the flow so its subscribable from withing the viewmodel |
| [getRandomMovie](get-random-movie.md) | `fun getRandomMovie(): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`<br>Example repository method that implements SimpleBounding and returns the flow so its subscribable from withing the viewmodel |
