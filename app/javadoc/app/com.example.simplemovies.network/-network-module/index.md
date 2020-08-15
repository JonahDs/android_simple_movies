[app](../../index.md) / [com.example.simplemovies.network](../index.md) / [NetworkModule](./index.md)

# NetworkModule

`@Module object NetworkModule`

A dagger modules provides information on how to construct components needed in the component
or dependency graph.

### Functions

| Name | Summary |
|---|---|
| [provideDataManager](provide-data-manager.md) | `fun provideDataManager(): `[`DataManager`](../../com.example.simplemovies.utils/-data-manager/index.md)<br>Provides a Datamanager instance |
| [provideMoshi](provide-moshi.md) | `fun provideMoshi(): Moshi`<br>Provides a Moshi instance |
| [provideOkHttpClient](provide-ok-http-client.md) | `fun provideOkHttpClient(): OkHttpClient`<br>Provides a OkHttpClient instance |
| [provideRetrofit](provide-retrofit.md) | `fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit`<br>Provides a Retrofit instance |
| [provideService](provide-service.md) | `fun provideService(retrofit: Retrofit): `[`TmdbApiService`](../-tmdb-api-service/index.md)<br>Provides a TmdbApiService based on retrofit |
