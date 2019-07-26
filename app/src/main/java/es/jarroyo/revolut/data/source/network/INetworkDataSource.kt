package es.jarroyo.revolut.data.source.network

import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.utils.NetworkSystemAbstract

open abstract class INetworkDataSource(private val networkSystem: NetworkSystemAbstract) {

     /**
     * CURRENCY LIST
     */
    abstract suspend fun getCurrencyList(currency: Currency): Response<CurrencyListResponse>


}