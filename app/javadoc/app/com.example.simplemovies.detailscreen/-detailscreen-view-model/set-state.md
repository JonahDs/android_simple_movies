[app](../../index.md) / [com.example.simplemovies.detailscreen](../index.md) / [DetailscreenViewModel](index.md) / [setState](./set-state.md)

# setState

`fun setState(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set the  only if parameters are not equal or untouched (null, "").
This check prevents a configuration change to call the repository (and possibly the API)
again while in no usecase this should happen.

### Parameters

`type` - fetch type, movie or tv

`id` - movie or tv id