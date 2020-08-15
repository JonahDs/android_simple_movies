[app](../../index.md) / [com.example.simplemovies.search](../index.md) / [SearchLandingViewModel](index.md) / [setQuery](./set-query.md)

# setQuery

`fun setQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set the  only if parameters are not equal or untouched (null, "").
This check prevents a configuration change to call the repository (and possibly the API)
again while in no usecase this should happen.

### Parameters

`query` - search query