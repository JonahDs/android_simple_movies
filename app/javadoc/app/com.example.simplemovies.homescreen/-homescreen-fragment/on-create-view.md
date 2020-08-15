[app](../../index.md) / [com.example.simplemovies.homescreen](../index.md) / [HomescreenFragment](index.md) / [onCreateView](./on-create-view.md)

# onCreateView

`fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?`

Creates and returns the view hierarchy associated with the fragment.

### Parameters

`inflater` - LayoutInflater object that can be used to inflate any views in the fragment

`container` - of non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view. This value may be null.

`savedInstanceState` - If non-null, this fragment is being re-constructed from a previous saved state as given here.

**Return**
the View for the fragment's UI, or null.

