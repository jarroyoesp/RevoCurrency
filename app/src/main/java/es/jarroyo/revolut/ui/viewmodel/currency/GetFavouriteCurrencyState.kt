package es.jarroyo.revolut.ui.viewmodel.currency

import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency


sealed class GetFavouriteCurrencyState {
    abstract val response: Response<Currency>?
}
data class SuccessGetFavouriteCurrencyState(override val response: Response<Currency>) : GetFavouriteCurrencyState()
data class LoadingGetFavouriteCurrencyState(override val response: Response<Currency>? = null) : GetFavouriteCurrencyState()
data class ErrorGetFavouriteCurrencyState(override val response: Response<Currency>) : GetFavouriteCurrencyState()