[app](../../index.md) / [com.example.simplemovies.utils](../index.md) / [DataManager](index.md) / [shouldRefresh](./should-refresh.md)

# shouldRefresh

`@Synchronized fun shouldRefresh(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Called to check if the data should be refreshed for given key

### Parameters

`key` - map identifier

**Syncronzed**
Executes the given function block while holding the monitor of the given object lock.

**Return**
boolean if the data should be refreshed

