[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [Resource](./index.md)

# Resource

`sealed class Resource<T>`

Class to wrap our data in together with an API status, this way our viewmodels can easily
set everything

### Parameters

`data` - generic type for representing the data default null

`status` - APIStatus default null

### Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | `class Error<T> : `[`Resource`](./index.md)`<`[`T`](-error/index.md#T)`>` |
| [Loading](-loading/index.md) | `class Loading<T> : `[`Resource`](./index.md)`<`[`T`](-loading/index.md#T)`>` |
| [Success](-success/index.md) | `class Success<T> : `[`Resource`](./index.md)`<`[`T`](-success/index.md#T)`>` |

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: `[`T`](index.md#T)`?`<br>generic type for representing the data default null |
| [status](status.md) | `val status: `[`APIStatus`](../-a-p-i-status/index.md)`?`<br>APIStatus default null |

### Inheritors

| Name | Summary |
|---|---|
| [Error](-error/index.md) | `class Error<T> : `[`Resource`](./index.md)`<`[`T`](-error/index.md#T)`>` |
| [Loading](-loading/index.md) | `class Loading<T> : `[`Resource`](./index.md)`<`[`T`](-loading/index.md#T)`>` |
| [Success](-success/index.md) | `class Success<T> : `[`Resource`](./index.md)`<`[`T`](-success/index.md#T)`>` |
