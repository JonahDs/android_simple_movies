[app](../../index.md) / [com.example.simplemovies.database](../index.md) / [GenreDao](./index.md)

# GenreDao

`abstract class GenreDao`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GenreDao()` |

### Functions

| Name | Summary |
|---|---|
| [getAll](get-all.md) | `abstract fun getAll(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreDb`](../-genre-db/index.md)`>>` |
| [getAllFlowDistinct](get-all-flow-distinct.md) | `fun getAllFlowDistinct(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreDb`](../-genre-db/index.md)`>>`<br>We don't want any unneeded overhead, by nature a flow will always trigger whenever any row in the table is updated. By calling distinctUntilChanged we ensure that the UI will only get updated when the data actually changes |
| [insert](insert.md) | `abstract fun insert(genres: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreDb`](../-genre-db/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
