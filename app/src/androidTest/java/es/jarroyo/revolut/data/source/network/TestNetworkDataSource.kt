package es.jarroyo.revolut.data.source.network

import android.accounts.NetworkErrorException
import es.jarroyo.revolut.data.factory.TestCurrencyListFactory
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException


class TestNetworkDataSource(private val networkSystem: NetworkSystemAbstract) : INetworkDataSource(networkSystem) {

    override suspend fun getCurrencyList(query: String): Response<CurrencyListResponse> {
        if (networkSystem.isNetworkAvailable()) {
            val response = TestCurrencyListFactory().createCurrencyListResponse1()
            response.currencyList.add(0, Currency(query, 1.0))
            return Response.Success(response)
        } else {
            return Response.Error(NetworkErrorException())
        }
    }

    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                val response = chain.proceed(request)

                // todo deal with the issues the way you need to
                if (response.code() == 500) {
                    return response
                }

                return response
            }
        })
        .build()

    companion object {
    }

}