# Simple movies
Simple movies is an app built to provide you with all sorts of movie information.

The app is built following the [MVVM pattern](https://developer.android.com/jetpack/guide) and following components:

* [Retrofit](https://square.github.io/retrofit/) to connect the app to the internet.
* [Moshi](https://github.com/square/moshi) to convert API results into workable objects.
* [Room](https://developer.android.com/training/data-storage/room) to store data locally.
* [Glide](https://github.com/bumptech/glide) to cache and load images from URI's.
* [Flow](https://codelabs.developers.google.com/codelabs/advanced-kotlin-coroutines/#7) to hande async data.
* [Dagger2](https://dagger.dev/dev-guide/android.html) to manage dependency injection.

# Why the MVVM pattern?
To leverage the seperation of concerns. Views should only contain UI and OS related events, a lean view likely decreases lifecycle or configuration changes related bugs.

# Why use Flow?
The application gets its information from a single source of thruth. The object that manages this emits multiple values corresponding to the API state.
Since the viewmodels doesn't know *when* these will be emitted it needs to listen to the datastream. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
can achieve this, the only problem is that livedata needs an observer, which is likely set in the view. Since observation of the livedata happens in the view it will call the
livedata *again* after a configuration change. This causes the refetch of data from either the API or database, which is an unwanted behaviour.
Using flow we can subscribe in the viewmodel to the datastream and manage incoming emits without the view having to know about it.

# Installing the application
You will need:
- Android studio
- Gradle

Simply clone the repository using following command
```bash
git clone https://github.com/JonahDs/android_simple_movies.git
```

Open it with Android Studio and build the APK to run it on your local device or emulator.

