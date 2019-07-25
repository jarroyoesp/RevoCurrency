package es.jarroyo.revolut.domain.usecase.currency.getCurrencyList

import es.jarroyo.revolut.domain.usecase.base.BaseRequest

class GetCurrencyListRequest(var query: String) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}