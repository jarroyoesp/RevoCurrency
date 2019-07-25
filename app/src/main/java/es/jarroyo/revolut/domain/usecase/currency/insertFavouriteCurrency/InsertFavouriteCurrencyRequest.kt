package es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency

import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.usecase.base.BaseRequest

class InsertFavouriteCurrencyRequest(var currency: Currency) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}