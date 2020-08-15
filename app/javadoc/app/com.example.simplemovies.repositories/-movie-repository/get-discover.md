[app](../../index.md) / [com.example.simplemovies.repositories](../index.md) / [MovieRepository](index.md) / [getDiscover](./get-discover.md)

# getDiscover

`fun getDiscover(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "movie", genresInc: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, genresExl: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): Flow<`[`Resource`](../../com.example.simplemovies.network/-resource/index.md)`<`[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)`>>`

Example repository method that implements SimpleBounding and returns the flow so its
subscribable from withing the viewmodel

.flowOn(IO) indicates that all code will be executed on the IO thread to prevent the MAIN
thread from doing to much work

### Parameters

`type` - movie or tv DEFAULT movie

`genresInc` - list of genres to be included

`genresExl` - list of genres to be excluded

`score` - minimal userscore DEFAULT 0

**Return**
Flow&lt;Resource&gt;

