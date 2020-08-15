[app](../../index.md) / [com.example.simplemovies.utils](../index.md) / [DataManager](./index.md)

# DataManager

`class DataManager`

Class that holds a map and returns true or false if the timer is over

Used to help decide if data should get refreshed, each repository method implementing
NetworkBounding sets a key and timer

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DataManager()`<br>Class that holds a map and returns true or false if the timer is over |

### Functions

| Name | Summary |
|---|---|
| [declareTimeout](declare-timeout.md) | `fun declareTimeout(timeout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, timeUnit: `[`TimeUnit`](https://developer.android.com/reference/java/util/concurrent/TimeUnit.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Converts long to ms |
| [shouldRefresh](should-refresh.md) | `fun shouldRefresh(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Called to check if the data should be refreshed for given key |
