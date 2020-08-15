[app](../../index.md) / [com.example.simplemovies.utils](../index.md) / [MovieAdapter](./index.md)

# MovieAdapter

`class MovieAdapter : ListAdapter<`[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`, `[`MovieViewHolder`](-movie-view-holder/index.md)`>`

Movie adapter

### Types

| Name | Summary |
|---|---|
| [DiffCallback](-diff-callback/index.md) | `companion object DiffCallback : ItemCallback<`[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`>` |
| [MovieViewHolder](-movie-view-holder/index.md) | `class MovieViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MovieAdapter(click: `[`OnClickListener`](../-on-click-listener/index.md)`)`<br>Movie adapter |

### Properties

| Name | Summary |
|---|---|
| [click](click.md) | `val click: `[`OnClickListener`](../-on-click-listener/index.md) |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`MovieViewHolder`](-movie-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`MovieViewHolder`](-movie-view-holder/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`, newItem: `[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`, newItem: `[`MovieNetwork`](../../com.example.simplemovies.domain/-movie-network/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
