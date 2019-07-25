package es.jarroyo.revolut.domain.usecase.currency.getCurrencyList

import es.jarroyo.revolut.data.repository.CurrencyRepository
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.domain.usecase.base.BaseUseCase

open class GetCurrencyListUseCase(val repository: CurrencyRepository) : BaseUseCase<GetCurrencyListRequest, CurrencyListResponse>() {

    override suspend fun run(): Response<CurrencyListResponse> {
        return repository.getCurrencyList(request!!)
    }
}