[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ApplicationModule](./index.md)

# ApplicationModule

`@Module object ApplicationModule`

### Functions

| Name | Summary |
|---|---|
| [provideDatabase](provide-database.md) | `fun provideDatabase(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)<br>Tell Dagger how to create the  |
| [provideGenreRepo](provide-genre-repo.md) | `fun provideGenreRepo(service: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, database: `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`): `[`GenreRepository`](../../com.example.simplemovies.repositories/-genre-repository/index.md)<br>Tell Dagger how to create the  |
| [provideMovieRepo](provide-movie-repo.md) | `fun provideMovieRepo(service: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, database: `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`): `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)<br>Tell Dagger how to create the  |
