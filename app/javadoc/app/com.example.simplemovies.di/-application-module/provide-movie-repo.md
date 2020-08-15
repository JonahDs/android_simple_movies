[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ApplicationModule](index.md) / [provideMovieRepo](./provide-movie-repo.md)

# provideMovieRepo

`@Singleton @JvmStatic @Provides fun provideMovieRepo(service: `[`TmdbApiService`](../../com.example.simplemovies.network/-tmdb-api-service/index.md)`, database: `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)`, dataManager: `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)`): `[`MovieRepository`](../../com.example.simplemovies.repositories/-movie-repository/index.md)

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

