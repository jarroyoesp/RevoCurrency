package es.jarroyo.revolut.domain.model.currency

import es.jarroyo.revolut.R
import es.jarroyo.revolut.data.entity.CurrencyEntity

data class CurrencyListResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    var currencyList: MutableList<Currency>
)

data class Currency(
    val currencyName: String,
    val value: Double = 0.0,
    val flagDrawable: Int = R.drawable.eur) {
    companion object {
        fun toEntity(currency: Currency): CurrencyEntity {
            return CurrencyEntity(currencyName = currency.currencyName)
        }
    }
}

data class Rates(
    val AUD: Double,
    val BGN: Double,
    val BRL: Double,
    val CAD: Double,
    val CHF: Double,
    val CNY: Double,
    val CZK: Double,
    val DKK: Double,
    val EUR: Double,
    val GBP: Double,
    val HKD: Double,
    val HRK: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val INR: Double,
    val ISK: Double,
    val JPY: Double,
    val KRW: Double,
    val MXN: Double,
    val MYR: Double,
    val NOK: Double,
    val NZD: Double,
    val PHP: Double,
    val PLN: Double,
    val RON: Double,
    val RUB: Double,
    val SEK: Double,
    val SGD: Double,
    val THB: Double,
    val TRY: Double,
    val USD: Double,
    val ZAR: Double
) {
    companion object {
        // PATH
        const val CURRENCY_LIST_SIZE = 31

        fun getFlag(currencyId: String): Int {
            var drawableInt = R.drawable.eur
            when(currencyId) {
                "AUD" -> drawableInt = R.drawable.aud
                "BGN" -> drawableInt = R.drawable.bgn
                "BRL" -> drawableInt = R.drawable.brl
                "CAD" -> drawableInt = R.drawable.cad
                "CHF" -> drawableInt = R.drawable.chf
                "CNY" -> drawableInt = R.drawable.cny
                "CZK" -> drawableInt = R.drawable.czk
                "DKK" -> drawableInt = R.drawable.dkk
                "EUR" -> drawableInt = R.drawable.eur
                "GBP" -> drawableInt = R.drawable.gbp
                "HKD" -> drawableInt = R.drawable.hkd
                "HRK" -> drawableInt = R.drawable.hrk
                "HUF" -> drawableInt = R.drawable.huf
                "IDR" -> drawableInt = R.drawable.idr
                "ILS" -> drawableInt = R.drawable.ils
                "INR" -> drawableInt = R.drawable.inr
                "ISK" -> drawableInt = R.drawable.isk
                "JPY" -> drawableInt = R.drawable.jpy
                "KRW" -> drawableInt = R.drawable.krw
                "MXN" -> drawableInt = R.drawable.mxn
                "MYR" -> drawableInt = R.drawable.myr
                "NOK" -> drawableInt = R.drawable.nok
                "NZD" -> drawableInt = R.drawable.nzd
                "PHP" -> drawableInt = R.drawable.php
                "PLN" -> drawableInt = R.drawable.pln
                "RON" -> drawableInt = R.drawable.ron
                "RUB" -> drawableInt = R.drawable.rub
                "SEK" -> drawableInt = R.drawable.sek
                "SGD" -> drawableInt = R.drawable.sgd
                "THB" -> drawableInt = R.drawable.thb
                "TRY" -> drawableInt = R.drawable.try_flag
                "USD" -> drawableInt = R.drawable.usd
                "ZAR" -> drawableInt = R.drawable.zar
            }

            return drawableInt
        }


    }

    fun ratesToRateList(): MutableList<Currency> {
        var rateList = arrayListOf<Currency>()

        var rateAud = Currency("AUD", AUD, getFlag("AUD"))
        rateList.add(rateAud)

        var rateBGN = Currency("BGN", BGN, getFlag("BGN"))
        rateList.add(rateBGN)

        var rateBRL = Currency("BRL", BRL, getFlag("BRL"))
        rateList.add(rateBRL)

        var rateCAD = Currency("CAD", CAD, getFlag("CAD"))
        rateList.add(rateCAD)

        var rateCHF = Currency("CHF", CHF, getFlag("CHF"))
        rateList.add(rateCHF)

        var rateCNY = Currency("CNY", CNY, getFlag("CNY"))
        rateList.add(rateCNY)

        var rateCZK = Currency("CZK", CZK, getFlag("CZK"))
        rateList.add(rateCZK)

        var rateDKK = Currency("DKK", DKK, getFlag("DKK"))
        rateList.add(rateDKK)

        var rateEUR = Currency("EUR", EUR, getFlag("EUR"))
        rateList.add(rateEUR)

        var rateGBP = Currency("GBP", GBP, getFlag("GBP"))
        rateList.add(rateGBP)

        var rateHKD = Currency("HKD", HKD, getFlag("HKD"))
        rateList.add(rateHKD)

        var rateHRK = Currency("HRK", HRK, getFlag("HRK"))
        rateList.add(rateHRK)

        var rateHUF = Currency("HUF", HUF, getFlag("HUF"))
        rateList.add(rateHUF)

        var rateIDR = Currency("IDR", IDR, getFlag("IDR"))
        rateList.add(rateIDR)

        var rateILS = Currency("ILS", ILS, getFlag("ILS"))
        rateList.add(rateILS)

        var rateINR = Currency("INR", INR, getFlag("INR"))
        rateList.add(rateINR)

        var rateISK = Currency("ISK", ISK, getFlag("ISK"))
        rateList.add(rateISK)

        var rateJPY = Currency("JPY", JPY, getFlag("JPY"))
        rateList.add(rateJPY)

        var rateKRW = Currency("KRW", KRW, getFlag("KRW"))
        rateList.add(rateKRW)

        var rateMXN = Currency("MXN", MXN, getFlag("MXN"))
        rateList.add(rateMXN)

        var rateMYR = Currency("MYR", MYR, getFlag("MYR"))
        rateList.add(rateMYR)

        var rateNOK = Currency("NOK", NOK, getFlag("NOK"))
        rateList.add(rateNOK)

        var rateNZD = Currency("NZD", NZD, getFlag("NZD"))
        rateList.add(rateNZD)

        var ratePHP = Currency("PHP", PHP, getFlag("PHP"))
        rateList.add(ratePHP)

        var ratePLN = Currency("PLN", PLN, getFlag("PLN"))
        rateList.add(ratePLN)

        var rateRON = Currency("RON", RON, getFlag("RON"))
        rateList.add(rateRON)

        var rateRUB = Currency("RUB", RUB, getFlag("RUB"))
        rateList.add(rateRUB)

        var rateSEK = Currency("SEK", SEK, getFlag("SEK"))
        rateList.add(rateSEK)

        var rateSGD = Currency("SGD", SGD, getFlag("SGD"))
        rateList.add(rateSGD)

        var rateTHB = Currency("THB", THB, getFlag("THB"))
        rateList.add(rateTHB)

        var rateTRY = Currency("TRY", TRY, getFlag("TRY"))
        rateList.add(rateTRY)

        var rateUSD = Currency("USD", USD, getFlag("USD"))
        rateList.add(rateUSD)

        var rateZAR = Currency("ZAR", ZAR, getFlag("ZAR"))
        rateList.add(rateZAR)


        return rateList

    }
}