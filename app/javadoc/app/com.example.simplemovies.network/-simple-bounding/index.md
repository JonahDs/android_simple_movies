[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [SimpleBounding](./index.md)

# SimpleBounding

`abstract class SimpleBounding<T>`

SimpleBounding is an object that handles and emits the correct values for the repositories.
This way the viewmodel can get rid of a bunch of boilerplate code like

scope.launch {
try {
    api = loading
    res = repo.call()
    api = done
} catch()
{
    api = error
}
}

The viewmodels will only need to observe the repositories and handle incoming events.

SimpleBounding only has one overridable method: makeApiCall() since no database interaction
will happen (repo's implementing simplebounding will ALWAYS fetch from the internet).

flows:
emit loading -&gt; emit done with data (internet is available)
emit loading -&gt; emit error (internet not available)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SimpleBounding()`<br>SimpleBounding is an object that handles and emits the correct values for the repositories. This way the viewmodel can get rid of a bunch of boilerplate code like |

### Functions

| Name | Summary |
|---|---|
| [asFlow](as-flow.md) | `fun asFlow(): Flow<`[`Resource`](../-resource/index.md)`<`[`T`](index.md#T)`>>` |
| [makeApiCall](make-api-call.md) | `abstract suspend fun makeApiCall(): `[`T`](index.md#T) |
