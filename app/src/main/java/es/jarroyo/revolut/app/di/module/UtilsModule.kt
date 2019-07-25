package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.app.notification.NotificationTDDManager
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.utils.NetworkSystem
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class UtilsModule {
    @Provides
    @Singleton
    fun provideNetworkSystem(app: App) =
            NetworkSystem(app) as NetworkSystemAbstract

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

    @Provides
    @Singleton
    fun provideNotificationManager(app: App)
            = NotificationTDDManager(app)
}