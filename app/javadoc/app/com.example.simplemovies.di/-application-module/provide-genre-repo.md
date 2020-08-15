[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ApplicationModule](index.md) / [provideGenreRepo](./provide-genre-repo.md)

# provideGenreRepo

`@Singleton @JvmStatic @Provides fun provideGenreRepo(service: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, database: `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`): `[`GenreRepository`](../../com.example.simplemovies.repositories/-genre-repository/index.md)

Tell Dagger how to create the

### Parameters

`service` - TmdbApiService instance

`dataManager` - SimpleMovieDatabase instance

`database` - DataManager instance

**Singleton**
to only create one instance inside given scope

**JvmStatic**
compile to a static function for performance increase

**Provides**
tell Dagger that this method needs to be called when  is needed

