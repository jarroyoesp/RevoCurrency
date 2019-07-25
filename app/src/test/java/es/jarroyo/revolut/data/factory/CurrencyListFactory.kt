package es.jarroyo.revolut.data.factory

import android.content.Context
import com.google.gson.Gson
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse

object CurrencyListFactory {

    fun createCurrencyListResponse1(context: Context): CurrencyListResponse {
        var gson = Gson()
        val json = response
        return gson?.fromJson(json, CurrencyListResponse::class.java)
    }

    val response = "{\n" +
        "\t\"base\": \"EUR\",\n" +
                "\t\"date\": \"2018-09-06\",\n" +
                "\t\"rates\": {\n" +
                "\t\t\"AUD\": 1.617,\n" +
                "\t\t\"BGN\": 1.9566,\n" +
                "\t\t\"BRL\": 4.7937,\n" +
                "\t\t\"CAD\": 1.5344,\n" +
                "\t\t\"CHF\": 1.128,\n" +
                "\t\t\"CNY\": 7.9483,\n" +
                "\t\t\"CZK\": 25.725,\n" +
                "\t\t\"DKK\": 7.4597,\n" +
                "\t\t\"GBP\": 0.8986,\n" +
                "\t\t\"HKD\": 9.1361,\n" +
                "\t\t\"HRK\": 7.4371,\n" +
                "\t\t\"HUF\": 326.62,\n" +
                "\t\t\"IDR\": 17330.0,\n" +
                "\t\t\"ILS\": 4.1723,\n" +
                "\t\t\"INR\": 83.751,\n" +
                "\t\t\"ISK\": 127.85,\n" +
                "\t\t\"JPY\": 129.6,\n" +
                "\t\t\"KRW\": 1305.3,\n" +
                "\t\t\"MXN\": 22.374,\n" +
                "\t\t\"MYR\": 4.8139,\n" +
                "\t\t\"NOK\": 9.7799,\n" +
                "\t\t\"NZD\": 1.764,\n" +
                "\t\t\"PHP\": 62.617,\n" +
                "\t\t\"PLN\": 4.32,\n" +
                "\t\t\"RON\": 4.6404,\n" +
                "\t\t\"RUB\": 79.607,\n" +
                "\t\t\"SEK\": 10.595,\n" +
                "\t\t\"SGD\": 1.6006,\n" +
                "\t\t\"THB\": 38.145,\n" +
                "\t\t\"TRY\": 7.6313,\n" +
                "\t\t\"USD\": 1.1639,\n" +
                "\t\t\"ZAR\": 17.83\n" +
                "\t}\n" +
                "}"
}