[app](../index.md) / [com.example.simplemovies.network](./index.md)

## Package com.example.simplemovies.network

### Types

| Name | Summary |
|---|---|
| [APIStatus](-a-p-i-status/index.md) | `enum class APIStatus`<br>enumeration for the API states |
| [NetworkBounding](-network-bounding/index.md) | `abstract class NetworkBounding<T>`<br>Networkbounding is an 'advanced' version of SimpleBounding. Motivation behind this is the same tho. |
| [NetworkModule](-network-module/index.md) | `object NetworkModule`<br>A dagger modules provides information on how to construct components needed in the component or dependency graph. |
| [Resource](-resource/index.md) | `sealed class Resource<T>`<br>Class to wrap our data in together with an API status, this way our viewmodels can easily set everything |
| [SimpleBounding](-simple-bounding/index.md) | `abstract class SimpleBounding<T>`<br>SimpleBounding is an object that handles and emits the correct values for the repositories. This way the viewmodel can get rid of a bunch of boilerplate code like |
| [TmdbApiService](-tmdb-api-service/index.md) | `interface TmdbApiService` |
