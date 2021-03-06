[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [MovieRepository](index.md) / [getMovieCast](./get-movie-cast.md)

# getMovieCast

`fun getMovieCast(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`CastWrapper`](../../com.example.simplemovies.domain/-cast-wrapper/index.md)`>>`

Example repository method that implements SimpleBounding and returns the flow so its
subscribable from withing the viewmodel

.flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
thread from doing to much work

### Parameters

`type` - movie or tv

`id` - movie or tv id

**Return**
Flow&lt;Resource&gt;

