package es.jarroyo.revolut.data.source.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import es.jarroyo.revolut.BuildConfig
import es.jarroyo.revolut.R
import es.jarroyo.revolut.data.exception.NetworkConnectionException
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.utils.NetworkSystemAbstract
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class NetworkDataSource(val context: Context, private val networkSystem: NetworkSystemAbstract) :
    INetworkDataSource() {

    /**
     * GET CURRENCY LIST
     */
    override suspend fun getCurrencyList(currency: Currency): Response<CurrencyListResponse> {
        if (networkSystem.isNetworkAvailable()) {
            val revolutCurrencyAPI = initRetrofitRevolutAPI()
            return try {
                val response =
                    revolutCurrencyAPI.getCurrencyList(currency.currencyName)
                        .await()

                var rateList = response.rates.ratesToRateList()
                rateList = rateList.filter { currencyFilter -> currencyFilter.currencyName != currency.currencyName } as MutableList<Currency>
                response.currencyList = rateList

                response.currencyList.add(0, currency)

                Response.Success(response)
            } catch (e: Exception) {
                Response.Error(e)
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

        return retrofit.create(RevolutCurrencyAPI::class.java)
    }


    private var okHttpClient = OkHttpClient.Builder()
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