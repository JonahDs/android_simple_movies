[app](../../index.md) / [com.example.simplemovies.utils](../index.md) / [CastAdapter](./index.md)

# CastAdapter

`class CastAdapter : ListAdapter<`[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`, `[`CastViewHolder`](-cast-view-holder/index.md)`>`

Cast recyclerview adapter

### Types

| Name | Summary |
|---|---|
| [CastViewHolder](-cast-view-holder/index.md) | `class CastViewHolder : ViewHolder` |
| [DiffCallback](-diff-callback/index.md) | `companion object DiffCallback : ItemCallback<`[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CastAdapter()`<br>Cast recyclerview adapter |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`CastViewHolder`](-cast-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CastViewHolder`](-cast-view-holder/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`, newItem: `[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`, newItem: `[`CastMember`](../../com.example.simplemovies.domain/-cast-member/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
