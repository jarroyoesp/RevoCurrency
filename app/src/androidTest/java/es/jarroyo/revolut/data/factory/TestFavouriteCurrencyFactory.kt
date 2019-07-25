package es.jarroyo.revolut.data.factory

import es.jarroyo.revolut.domain.model.currency.Currency

class TestFavouriteCurrencyFactory {

    companion object {
        val FAVOURITE_CURRENCY_NAME = "EUR"


        fun createFavouriteCurrency(): Currency {
            return Currency(currencyName = FAVOURITE_CURRENCY_NAME)
        }
    }
}