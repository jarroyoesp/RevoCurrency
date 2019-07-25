package es.jarroyo.revolut.data.factory

import android.content.Context
import com.google.gson.Gson
import es.jarroyo.revolut.data.utils.Utils
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse

object CurrencyListFactory {

    fun createCurrencyListResponse1(context: Context): CurrencyListResponse {
        var gson = Gson()
        val json = Utils.readJsonFromAssets(context, "response1.json")
        val response =  gson?.fromJson(json, CurrencyListResponse::class.java)

        val rateList = response.rates.ratesToRateList()
        response.currencyList = rateList

        return response
    }
}