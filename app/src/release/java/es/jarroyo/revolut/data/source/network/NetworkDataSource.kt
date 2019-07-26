package es.jarroyo.revolut.data.source.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import es.jarroyo.revolut.BuildConfig
import es.jarroyo.revolut.R
import es.jarroyo.revolut.data.exception.NetworkConnectionException
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.domain.model.currency.Rates
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


class NetworkDataSource(val context: Context, private val networkSystem: NetworkSystemAbstract) :
    INetworkDataSource(networkSystem) {

    /**
     * GET CURRENCY LIST
     */
    override suspend fun getCurrencyList(query: String): Response<CurrencyListResponse> {
        if (networkSystem.isNetworkAvailable()) {
            val revolutCurrencyAPI = initRetrofitRevolutAPI()
            try {
                val response =
                    revolutCurrencyAPI.getCurrencyList(query)
                        .await()

                var rateList = response.rates.ratesToRateList()
                rateList = rateList.filter { currency -> !currency.currencyName.equals(query) } as MutableList<Currency>
                response.currencyList = rateList

                response.currencyList.add(0, Currency(query, 1.0, Rates.getFlag(query)))

                return Response.Success(response)
            } catch (e: Exception) {
                return Response.Error(e)
            }
        } else {
            return Response.Error(NetworkConnectionException(context.getString(R.string.error_no_internet)))
        }
    }


    private fun initRetrofitRevolutAPI(): RevolutCurrencyAPI {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.REVOLUT_URL_BASE)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }.build()

        val revolutAPI = retrofit.create(RevolutCurrencyAPI::class.java)
        return revolutAPI
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
        }).hostnameVerifier(object : HostnameVerifier {
            override fun verify(p0: String?, p1: SSLSession?): Boolean {
                return true
            }
        })
        .build()

}