[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [SimpleBounding](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`SimpleBounding()`

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

