[app](../../index.md) / [com.example.simplemovies.utils](../index.md) / [CrewExtendedAdapter](./index.md)

# CrewExtendedAdapter

`class CrewExtendedAdapter : ListAdapter<`[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`, `[`CrewViewHolder`](-crew-view-holder/index.md)`>`

Crew extended adapter

### Types

| Name | Summary |
|---|---|
| [CrewViewHolder](-crew-view-holder/index.md) | `class CrewViewHolder : ViewHolder` |
| [DiffCallback](-diff-callback/index.md) | `companion object DiffCallback : ItemCallback<`[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CrewExtendedAdapter()`<br>Crew extended adapter |

### Functions

| Name | Summary |
|---|---|
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`CrewViewHolder`](-crew-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CrewViewHolder`](-crew-view-holder/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [areContentsTheSame](are-contents-the-same.md) | `fun areContentsTheSame(oldItem: `[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`, newItem: `[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [areItemsTheSame](are-items-the-same.md) | `fun areItemsTheSame(oldItem: `[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`, newItem: `[`CrewMember`](../../com.example.simplemovies.domain/-crew-member/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
