package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.app.notification.NotificationTDDManager
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import es.jarroyo.revolut.utils.TestNetworkSystem
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class TestUtilsModule {
    @Provides
    @Singleton
    fun provideNetworkSystem(app: App) =
            TestNetworkSystem(app) as NetworkSystemAbstract

    @Provides
    @Singleton
    fun provideCorutineContext() =
        Dispatchers.Default as CoroutineContext

    @Provides
    @Singleton
    fun provideNotificationManager(app: App)
            = NotificationTDDManager(app)
}