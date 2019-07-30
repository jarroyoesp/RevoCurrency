package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource
import es.jarroyo.revolut.data.source.network.INetworkDataSource
import es.jarroyo.revolut.data.source.network.NetworkDataSource
import es.jarroyo.revolut.ui.App
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideCacheSource() =
        CacheDataSource

    @Provides
    fun provideNetworkDataSource(appContext: App, networkSystemBase: NetworkSystemAbstract) =
            NetworkDataSource(appContext, networkSystemBase) as INetworkDataSource

    @Provides
    fun provideDiskDataSource(appContext: App) =
        DiskDataSource(appContext)
}
