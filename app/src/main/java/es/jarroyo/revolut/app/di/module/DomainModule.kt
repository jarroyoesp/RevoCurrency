package es.jarroyo.revolut.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListUseCase
import es.jarroyo.revolut.domain.usecase.currency.getFavouriteCurrency.GetFavouriteCurrencyUseCase
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyUseCase


@Module
class DomainModule {

    /**
     * CURRENCY
     */
    @Provides
    fun provideGetCurrencyListUseCase(repository: CurrencyRepository) = GetCurrencyListUseCase(repository)

    @Provides
    fun provideGetFavouriteCurrencyUseCase(repository: CurrencyRepository) = GetFavouriteCurrencyUseCase(repository)


    @Provides
    fun provideInsertFavouriteCurrencyUseCase(repository: CurrencyRepository) = InsertFavouriteCurrencyUseCase(repository)
}