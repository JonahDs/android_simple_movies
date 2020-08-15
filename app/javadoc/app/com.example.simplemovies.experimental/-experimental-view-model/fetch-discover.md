[app](../../index.md) / [com.example.simplemovies.experimental](../index.md) / [ExperimentalViewModel](index.md) / [fetchDiscover](./fetch-discover.md)

# fetchDiscover

`fun fetchDiscover(state: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, score: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, resource: `[`Resources`](https://developer.android.com/reference/android/content/res/Resources.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Subscribe to the repository call and catch it's values.

Create a 'settings' or configuration object based on given parameters.

genresInc = state is include? give checked chips, otherwise none
genresExl = state is exclude? give checked chips, otherwise none

### Parameters

`type` - fetch type, movie or tv

`state` - include/exclude genres

`score` - wanted user score

`resource` - Android resources to check on types