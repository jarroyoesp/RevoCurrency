package es.jarroyo.revolut.app.di


import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.app.navigator.Navigator
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.ui.RevolutCurrencyApp
import javax.inject.Singleton


@Module
class TestApplicationModule(val app: RevolutCurrencyApp){
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()


}