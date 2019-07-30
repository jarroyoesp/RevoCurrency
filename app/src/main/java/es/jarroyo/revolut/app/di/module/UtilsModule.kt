package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.utils.NetworkSystem
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
class UtilsModule {
    @Provides
    fun provideNetworkSystem(app: App) =
            NetworkSystem(app) as NetworkSystemAbstract

    @Provides
    fun provideCoroutineContext() =
        Dispatchers.Default as CoroutineContext

}