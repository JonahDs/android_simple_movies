[app](../../index.md) / [com.example.simplemovies.homescreen](../index.md) / [HomescreenFragment](./index.md)

# HomescreenFragment

`class HomescreenFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HomescreenFragment()` |

### Properties

| Name | Summary |
|---|---|
| [viewModelfactory](view-modelfactory.md) | `lateinit var viewModelfactory: Factory` |

### Functions

| Name | Summary |
|---|---|
| [onAttach](on-attach.md) | `fun onAttach(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>First method that gets called when a fragment is associated with its activity inside here we setup the dagger component that will handle this fragment and viewmodel |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?`<br>Creates and returns the view hierarchy associated with the fragment. |
