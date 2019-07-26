package es.jarroyo.revolut.domain.usecase.currency.getCurrencyList

import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.usecase.base.BaseRequest

class GetCurrencyListRequest(var currency: Currency) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}