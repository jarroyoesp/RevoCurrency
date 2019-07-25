package es.jarroyo.revolut.ui.viewmodel.currency

import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse


sealed class GetCurrencyListState {
    abstract val response: Response<CurrencyListResponse>?
}
data class SuccessGetCurrencyListState(override val response: Response<CurrencyListResponse>) : GetCurrencyListState()
data class LoadingGetCurrencyListState(override val response: Response<CurrencyListResponse>? = null) : GetCurrencyListState()
data class ErrorGetCurrencyListState(override val response: Response<CurrencyListResponse>) : GetCurrencyListState()