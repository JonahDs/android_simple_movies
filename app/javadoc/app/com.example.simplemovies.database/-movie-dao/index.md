[app](../../index.md) / [com.example.simplemovies.database](../index.md) / [MovieDao](./index.md)

# MovieDao

`abstract class MovieDao`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MovieDao()` |

### Functions

| Name | Summary |
|---|---|
| [clearTable](clear-table.md) | `abstract fun clearTable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `abstract fun getAll(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MovieDb`](../-movie-db/index.md)`>>` |
| [getAllFlowDistinct](get-all-flow-distinct.md) | `fun getAllFlowDistinct(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MovieDb`](../-movie-db/index.md)`>>`<br>We don't want any unneeded overhead, by nature a flow will always trigger whenever any row in the table is updated. By calling distinctUntilChanged we ensure that the UI will only get updated when the data actually changes |
| [insert](insert.md) | `abstract fun insert(movies: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MovieDb`](../-movie-db/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
