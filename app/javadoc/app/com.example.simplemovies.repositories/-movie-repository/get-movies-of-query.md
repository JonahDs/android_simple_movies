[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [MovieRepository](index.md) / [getMoviesOfQuery](./get-movies-of-query.md)

# getMoviesOfQuery

`fun getMoviesOfQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`

Example repository method that implements SimpleBounding and returns the flow so its
subscribable from withing the viewmodel

.flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
thread from doing to much work

### Parameters

`query` - search query

**Return**
Flow&lt;Resource&gt;

