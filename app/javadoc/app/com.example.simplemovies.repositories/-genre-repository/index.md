[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [GenreRepository](./index.md)

# GenreRepository

`class GenreRepository`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GenreRepository(tmdbApiService: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, genreDao: `[`GenreDao`](../../com.example.simplemovies.database/-genre-dao/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [getGenres](get-genres.md) | `fun getGenres(): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`GenresWrapper`](../../com.example.simplemovies.domain/-genres-wrapper/index.md)`>>`<br>Example repository method that implements NetworkBounding and returns the flow so its subscribable from withing the viewmodel |
