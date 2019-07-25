package es.jarroyo.revolut.data.source.network

import android.content.Context
import es.jarroyo.revolut.data.factory.CurrencyListFactory
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.IOException


class NetworkDataSource(private val context: Context,
                        private val networkSystem: NetworkSystemAbstract
) : INetworkDataSource(networkSystem) {
    /**
     * GET CURRENCY LIST
     */
    override suspend fun getCurrencyList(query: String): Response<CurrencyListResponse> {
        delay(500)
        val response = CurrencyListFactory.createCurrencyListResponse1(context)
        response.currencyList.add(0, Currency(query, 1.0))
        return Response.Success(response)
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

}