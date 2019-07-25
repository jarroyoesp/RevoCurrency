package es.jarroyo.revolut.domain.usecase.currency.getFavouriteCurrency

import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.usecase.base.BaseUseCase

open class GetFavouriteCurrencyUseCase(val repository: CurrencyRepository) : BaseUseCase<Nothing, Currency>() {

    override suspend fun run(): Response<Currency> {
        return repository.getFavouriteCurrency()
    }
}