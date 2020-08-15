[app](../../index.md) / [com.example.simplemovies.database](../index.md) / [GenreDao](index.md) / [getAllFlowDistinct](./get-all-flow-distinct.md)

# getAllFlowDistinct

`fun getAllFlowDistinct(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreDb`](../-genre-db/index.md)`>>`

We don't want any unneeded overhead, by nature a flow will always trigger whenever any
row in the table is updated. By calling distinctUntilChanged we ensure that the UI will only
get updated when the data actually changes

