[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [TmdbApiService](index.md) / [getDiscover](./get-discover.md)

# getDiscover

`@GET("3/discover/{type}") abstract suspend fun getDiscover(@Path("type") type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "movie", @Query("with_genres") genreInc: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>? = listOf(), @Query("without_genres") genreExcl: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>? = listOf(), @Query("vote_average.gte") score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = 0, @Query("vote_average.lte") cielScore: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = 0): `[`MoviesWrapper`](../../com.example.simplemovies.domain/-movies-wrapper/index.md)

Get movies based on genres, type and vote averages

