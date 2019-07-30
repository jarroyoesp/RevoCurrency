package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import es.jarroyo.revolut.utils.TestNetworkSystem
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class TestUtilsModule {
    @Provides
    @Singleton
    fun provideNetworkSystem() =
            TestNetworkSystem() as NetworkSystemAbstract

    @Provides
    @Singleton
    fun provideCoroutineContext() =
        Dispatchers.Default as CoroutineContext

}