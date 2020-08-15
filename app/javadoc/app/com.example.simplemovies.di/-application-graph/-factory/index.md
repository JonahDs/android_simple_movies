[app](../../../index.md) / [com.example.simplemovies.di](../../index.md) / [ApplicationGraph](../index.md) / [Factory](./index.md)

# Factory

`@Factory interface Factory`

A factory for a component.
A factory is a type with a single method that returns a new component instance each time it is called.
The parameters of that method allow the caller to provide the modules, dependencies
and bound instances required by the component.

### Functions

| Name | Summary |
|---|---|
| [create](create.md) | `abstract fun create(applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`ApplicationGraph`](../index.md) |
