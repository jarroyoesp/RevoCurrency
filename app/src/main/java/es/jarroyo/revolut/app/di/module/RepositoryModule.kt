package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource
import es.jarroyo.revolut.data.source.network.INetworkDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideBooksRepository(
        cacheDataSource: CacheDataSource,
        diskDataSource: DiskDataSource,
        networkDataSource: INetworkDataSource
    ) = CurrencyRepository(cacheDataSource, diskDataSource, networkDataSource)

}