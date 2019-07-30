package es.jarroyo.revolut.data.repository

import es.jarroyo.revolut.data.entity.CurrencyEntity
import es.jarroyo.revolut.data.source.cache.CacheDataSource
import es.jarroyo.revolut.data.source.disk.DiskDataSource
import es.jarroyo.revolut.data.source.network.INetworkDataSource
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListRequest
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyRequest

class CurrencyRepository(
    private val cacheDataSource: CacheDataSource,
    private val diskDataSource: DiskDataSource,
    private val networkDataSource: INetworkDataSource
) {
    /***********************************************************************************************
     * GET CURRENCY LIST
     **********************************************************************************************/
    suspend fun getCurrencyList(request: GetCurrencyListRequest): Response<CurrencyListResponse> {
        return networkDataSource.getCurrencyList(request.currency)
    }

    /***********************************************************************************************
     * GET CURRENCY FAVOURITE
     **********************************************************************************************/
    suspend fun getFavouriteCurrency(): Response<Currency> {
        var currency = cacheDataSource.mFavouriteCurrency
        if (currency == null) {
            val currencyEntity = diskDataSource.getCurrency()
            if (currencyEntity != null) {
                currency = CurrencyEntity.toModel(currencyEntity)
            } else {
                currency = Currency(currencyName = "EUR", currencyText = "Euro")
                insertFavouriteCurrency(InsertFavouriteCurrencyRequest(currency))
            }
        }
        return Response.Success(currency)
    }

    /***********************************************************************************************
     * INSERT CURRENCY FAVOURITE
     **********************************************************************************************/
    fun insertFavouriteCurrency(request: InsertFavouriteCurrencyRequest): Response.Success<Currency> {
        cacheDataSource.mFavouriteCurrency = request.currency
        diskDataSource.insertCurrency(Currency.toEntity(request.currency))
        return Response.Success(request.currency)
    }
}