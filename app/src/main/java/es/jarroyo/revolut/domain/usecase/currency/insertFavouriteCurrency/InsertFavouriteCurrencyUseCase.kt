package es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency

import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.usecase.base.BaseUseCase

open class InsertFavouriteCurrencyUseCase(val repository: CurrencyRepository) : BaseUseCase<InsertFavouriteCurrencyRequest, Currency>() {

    override suspend fun run(): Response<Currency> {
        return repository.insertFavouriteCurrency(request!!)
    }
}