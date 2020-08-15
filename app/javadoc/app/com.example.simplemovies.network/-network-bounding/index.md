[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [NetworkBounding](./index.md)

# NetworkBounding

`abstract class NetworkBounding<T>`

Networkbounding is an 'advanced' version of SimpleBounding. Motivation behind this is the same tho.

repo's implementing this method should have database interactions, otherwise use SimpleBounding

NetworkBounding has four overridable methods
saveApiResToDb -&gt; save incoming api call data to the database
shouldFetch -&gt; needs the data inside the database an update?
fetchFromDb -&gt; get the data from the database
mapApiCall -&gt; call the internet

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NetworkBounding()`<br>Networkbounding is an 'advanced' version of SimpleBounding. Motivation behind this is the same tho. |

### Functions

| Name | Summary |
|---|---|
| [asFlow](as-flow.md) | `fun asFlow(): Flow<`[`Resource`](../-resource/index.md)`<`[`T`](index.md#T)`>>` |
| [fetchFromDb](fetch-from-db.md) | `abstract fun fetchFromDb(): Flow<`[`T`](index.md#T)`?>` |
| [makeApiCall](make-api-call.md) | `abstract suspend fun makeApiCall(): `[`T`](index.md#T) |
| [saveApiResToDb](save-api-res-to-db.md) | `abstract fun saveApiResToDb(item: `[`T`](index.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shouldFetch](should-fetch.md) | `abstract fun shouldFetch(data: `[`T`](index.md#T)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
