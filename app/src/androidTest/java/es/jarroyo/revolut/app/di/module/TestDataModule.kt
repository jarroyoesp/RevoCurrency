package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource
import es.jarroyo.revolut.data.source.network.INetworkDataSource
import es.jarroyo.revolut.data.source.network.TestNetworkDataSource
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import javax.inject.Singleton

@Module
class TestDataModule {

    @Provides @Singleton
    fun provideNetworkDataSource(appContext: App, networkSystemBase: NetworkSystemAbstract) =
            TestNetworkDataSource(networkSystemBase) as INetworkDataSource

    @Provides
    @Singleton
    fun provideCacheSource() =
        CacheDataSource

    @Provides
    @Singleton
    fun provideDiskDataSource(appContext: App) =
        DiskDataSource(appContext)
}
