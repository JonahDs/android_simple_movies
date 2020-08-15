[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [NetworkBounding](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`NetworkBounding()`

Networkbounding is an 'advanced' version of SimpleBounding. Motivation behind this is the same tho.

repo's implementing this method should have database interactions, otherwise use SimpleBounding

NetworkBounding has four overridable methods
saveApiResToDb -&gt; save incoming api call data to the database
shouldFetch -&gt; needs the data inside the database an update?
fetchFromDb -&gt; get the data from the database
mapApiCall -&gt; call the internet

