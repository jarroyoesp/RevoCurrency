# Revolut currency 

App that shows currency equivalence to a given favourite currency. The currency rates are updated every 1 second.

# Architecture

It has been decided to use MVVM pattern to separate UI from bussiness logic.
To async tasks it has been decided to use Kotlin coroutines.

# UseCases

1) GetFavouriteCurrency: get user's favourite currency from local (cache or disk - room). You can change it clicking on the different currencies when rates are shown.

2) GetCurrencyList: get currency rates for a base currency (your favourite)

3) InsertFavouriteCurrency: save on device your favourite currency for future requests.


## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Stable Kotlin Coroutines (1.3)](https://github.com/Kotlin/kotlinx.coroutines)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* Android Support Libraries
* [Dagger 2 (2.18)](https://github.com/google/dagger)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Leak Canary](https://github.com/square/leakcanary)

# Screenshots

<img src="https://github.com/jarroyoesp/RevolutCurrency/blob/master/images/currency_list.png" width="200">

