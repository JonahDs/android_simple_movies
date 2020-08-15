[app](../../index.md) / [com.example.simplemovies.di](../index.md) / [ApplicationModule](index.md) / [provideDatabase](./provide-database.md)

# provideDatabase

`@JvmStatic @Provides @Singleton fun provideDatabase(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`SimpleMovieDatabase`](../../com.example.simplemovies.database/-simple-movie-database/index.md)

Tell Dagger how to create the

### Parameters

`context` - application context

**Singleton**
to only create one instance inside given scope

**JvmStatic**
compile to a static function for performance increase

**Provides**
tell Dagger that this method needs to be called when  is needed

