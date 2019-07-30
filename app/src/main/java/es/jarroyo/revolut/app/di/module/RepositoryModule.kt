package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource
import es.jarroyo.revolut.data.source.network.INetworkDataSource

@Module
class RepositoryModule {
    @Provides
    fun provideCurrencyRepository(
        cacheDataSource: CacheDataSource,
        diskDataSource: DiskDataSource,
        networkDataSource: INetworkDataSource
    ) = CurrencyRepository(cacheDataSource, diskDataSource, networkDataSource)

}