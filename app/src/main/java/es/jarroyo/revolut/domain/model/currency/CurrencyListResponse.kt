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
    val flagDrawable: Int = R.drawable.eur,
    val currencyText: String = "") {
    companion object {
        fun toEntity(currency: Currency): CurrencyEntity {
            return CurrencyEntity(currencyName = currency.currencyName, currencyText = currency.currencyText)
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

        var rateAud = Currency("AUD", AUD, getFlag("AUD"), "Australian Dollar")
        rateList.add(rateAud)

        var rateBGN = Currency("BGN", BGN, getFlag("BGN"), "Bulgarian lev")
        rateList.add(rateBGN)

        var rateBRL = Currency("BRL", BRL, getFlag("BRL"), "Brazilian real")
        rateList.add(rateBRL)

        var rateCAD = Currency("CAD", CAD, getFlag("CAD"), "Canadian dollar")
        rateList.add(rateCAD)

        var rateCHF = Currency("CHF", CHF, getFlag("CHF"), "Swiss franc")
        rateList.add(rateCHF)

        var rateCNY = Currency("CNY", CNY, getFlag("CNY"), "Renminbi")
        rateList.add(rateCNY)

        var rateCZK = Currency("CZK", CZK, getFlag("CZK"),"Czech koruna")
        rateList.add(rateCZK)

        var rateDKK = Currency("DKK", DKK, getFlag("DKK"), "Danish krone")
        rateList.add(rateDKK)

        var rateEUR = Currency("EUR", EUR, getFlag("EUR"), "Euro")
        rateList.add(rateEUR)

        var rateGBP = Currency("GBP", GBP, getFlag("GBP"), "Pound sterling")
        rateList.add(rateGBP)

        var rateHKD = Currency("HKD", HKD, getFlag("HKD"), "Hong Kong dollar")
        rateList.add(rateHKD)

        var rateHRK = Currency("HRK", HRK, getFlag("HRK"), "Croatian kuna")
        rateList.add(rateHRK)

        var rateHUF = Currency("HUF", HUF, getFlag("HUF"), "Hungarian forint")
        rateList.add(rateHUF)

        var rateIDR = Currency("IDR", IDR, getFlag("IDR"), "Indonesian rupiah")
        rateList.add(rateIDR)

        var rateILS = Currency("ILS", ILS, getFlag("ILS"), "Israeli new shekel")
        rateList.add(rateILS)

        var rateINR = Currency("INR", INR, getFlag("INR"), "Indian rupee")
        rateList.add(rateINR)

        var rateISK = Currency("ISK", ISK, getFlag("ISK"), "Icelandic króna")
        rateList.add(rateISK)

        var rateJPY = Currency("JPY", JPY, getFlag("JPY"),"Japanese yen")
        rateList.add(rateJPY)

        var rateKRW = Currency("KRW", KRW, getFlag("KRW"), "South Korean won")
        rateList.add(rateKRW)

        var rateMXN = Currency("MXN", MXN, getFlag("MXN"), "Mexican peso")
        rateList.add(rateMXN)

        var rateMYR = Currency("MYR", MYR, getFlag("MYR"), "Malaysian ringgit")
        rateList.add(rateMYR)

        var rateNOK = Currency("NOK", NOK, getFlag("NOK"), "Norwegian krone")
        rateList.add(rateNOK)

        var rateNZD = Currency("NZD", NZD, getFlag("NZD"), "New Zealand dollar")
        rateList.add(rateNZD)

        var ratePHP = Currency("PHP", PHP, getFlag("PHP"), "Philippine peso")
        rateList.add(ratePHP)

        var ratePLN = Currency("PLN", PLN, getFlag("PLN"), "Polish złoty")
        rateList.add(ratePLN)

        var rateRON = Currency("RON", RON, getFlag("RON"), "Romanian leu")
        rateList.add(rateRON)

        var rateRUB = Currency("RUB", RUB, getFlag("RUB"), "Russian ruble")
        rateList.add(rateRUB)

        var rateSEK = Currency("SEK", SEK, getFlag("SEK"), "Swedish krona")
        rateList.add(rateSEK)

        var rateSGD = Currency("SGD", SGD, getFlag("SGD"), "Singapore dollar")
        rateList.add(rateSGD)

        var rateTHB = Currency("THB", THB, getFlag("THB"), "Thai baht")
        rateList.add(rateTHB)

        var rateTRY = Currency("TRY", TRY, getFlag("TRY"), "Turkish lira")
        rateList.add(rateTRY)

        var rateUSD = Currency("USD", USD, getFlag("USD"), "US Dollar")
        rateList.add(rateUSD)

        var rateZAR = Currency("ZAR", ZAR, getFlag("ZAR"), "South African rand")
        rateList.add(rateZAR)


        return rateList

    }
}