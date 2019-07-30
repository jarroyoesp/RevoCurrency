package es.jarroyo.revolut.data.source.network

import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse

open abstract class INetworkDataSource() {

     /**
     * CURRENCY LIST
     */
    abstract suspend fun getCurrencyList(currency: Currency): Response<CurrencyListResponse>


}