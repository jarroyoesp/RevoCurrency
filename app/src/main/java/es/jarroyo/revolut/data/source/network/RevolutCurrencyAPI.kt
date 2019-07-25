package es.jarroyo.revolut.data.source.network

import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RevolutCurrencyAPI {
    companion object {
        // PATH
        const val URL_PATH_LIST_CURRENCY = "latest"

        // PARAMETERS
        const val URL_PARAMETER_BASE = "base"
        const val URL_PARAMETER_MAX_RESULTS = "maxResults"
        const val URL_PARAMETER_CITY_NAME = "q"
        const val URL_PARAMETER_API_KEY = "key"
        const val URL_PARAMETER_UNITS = "units"
    }

    /**
     * CURRENT WEATHER
     */
    @Headers("Content-Type: application/json")
    @GET(RevolutCurrencyAPI.URL_PATH_LIST_CURRENCY)
    fun getCurrencyList(
        @Query(URL_PARAMETER_BASE) query: String = "EUR"
    ): Deferred<CurrencyListResponse>


}

