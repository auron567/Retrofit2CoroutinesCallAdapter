# Retrofit2 Coroutines CallAdapter
Sample Android application that show how can we create our own Retrofit `CallAdapter` for Coroutines to handle response as states.
## Implementation
By creating your own `CallAdapter`, you can do the following:
```kt
interface Service {
    @GET("movies/{id}")
    suspend fun getMovie(): NetworkResponse<Movie, MovieError>
}

class Repository {
    val movie = service.getMovie()
    
    when (movie) {
        is NetworkResponse.Success -> // Success response
        is NetworkResponse.ApiError -> // Failure response (non-2xx)
        is NetworkResponse.NetworkError -> // Network failure
        is NetworkResponse.UnknownError -> // Unexpected exceptions
    }
}
```
<br><br>
The implementation of `CallAdapter` in the application is defined through the following classes:
- [`NetworkResponse`](app/src/main/java/com/example/retrofit2coroutinescalladapter/data/network/adapter/NetworkResponse.kt) - Sealed class that represents the API call response states.
- [`NetworkResponseCall`](app/src/main/java/com/example/retrofit2coroutinescalladapter/data/network/adapter/NetworkResponseCall.kt) - `Call` interface implementation.
- [`NetworkResponseAdapter`](app/src/main/java/com/example/retrofit2coroutinescalladapter/data/network/adapter/NetworkResponseAdapter.kt) - `CallAdapter` interface implementation.
- [`NetworkResponseAdapterFactory`](app/src/main/java/com/example/retrofit2coroutinescalladapter/data/network/adapter/NetworkResponseAdapterFactory.kt) - `CallAdapter.Factory` abstract class implementation.

<br><br>
Also you need to make Retrofit aware of our custom `CallAdapter.Factory`:
```kt
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
```
## Architecture
This sample application uses [Architecture Components](https://developer.android.com/topic/libraries/architecture) to separate the UI code in `MainActivity` from the application logic in `MainViewModel`. `MovieRepository` (and its concrete `MovieRepositoryImpl` implementation) provides a bridge between the `ViewModel` and `MovieService`, which uses Retrofit to return a list of `Movie` objects.
## Libraries
- [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- Jetpack
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
-  [Retrofit2](https://github.com/square/retrofit)
- [OkHttp3](https://github.com/square/okhttp)
- [Koin](https://github.com/InsertKoinIO/koin)
- [Timber](https://github.com/JakeWharton/timber)
- [MockK](https://github.com/mockk/mockk)
## Usage
To use this sample application you need to register your own [TMDb API key](https://www.themoviedb.org/documentation/api) and add it to the file [`NetworkModule.kt`](app/src/main/java/com/example/retrofit2coroutinescalladapter/di/NetworkModule.kt):
```kt
const val API_KEY = "INSERT-API-KEY-HERE"
```
## References
- [Create Retrofit CallAdapter for Coroutines to handle response as states](https://proandroiddev.com/create-retrofit-calladapter-for-coroutines-to-handle-response-as-states-c102440de37a)
