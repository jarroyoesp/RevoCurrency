package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListUseCase
import es.jarroyo.revolut.domain.usecase.currency.getFavouriteCurrency.GetFavouriteCurrencyUseCase
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyUseCase
import javax.inject.Singleton


@Module
class DomainModule {

    /**
     * CURRENCY
     */
    @Provides
    @Singleton
    fun provideGetCurrencyListUseCase(repository: CurrencyRepository) = GetCurrencyListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavouriteCurrencyUseCase(repository: CurrencyRepository) = GetFavouriteCurrencyUseCase(repository)


    @Provides
    @Singleton
    fun provideInsertFavouriteCurrencyUseCase(repository: CurrencyRepository) = InsertFavouriteCurrencyUseCase(repository)
}