[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [MovieRepository](index.md) / [getMoviesOfFlow](./get-movies-of-flow.md)

# getMoviesOfFlow

`fun getMoviesOfFlow(): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`

Example repository method that implements NetworkBounding and returns the flow so its
subscribable from withing the viewmodel

.flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
thread from doing to much work

**Return**
Flow&lt;Resource&gt;

