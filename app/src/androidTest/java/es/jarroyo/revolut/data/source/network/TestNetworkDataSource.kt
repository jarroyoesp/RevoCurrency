package es.jarroyo.revolut.data.source.network

import android.accounts.NetworkErrorException
import es.jarroyo.revolut.data.factory.TestCurrencyListFactory
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.utils.NetworkSystemAbstract


class TestNetworkDataSource(private val networkSystem: NetworkSystemAbstract) : INetworkDataSource() {

    override suspend fun getCurrencyList(currency: Currency): Response<CurrencyListResponse> {
        if (networkSystem.isNetworkAvailable()) {
            val response = TestCurrencyListFactory().createCurrencyListResponse1()
            response.currencyList.add(0, currency)
            return Response.Success(response)
        } else {
            return Response.Error(NetworkErrorException())
        }
    }
}