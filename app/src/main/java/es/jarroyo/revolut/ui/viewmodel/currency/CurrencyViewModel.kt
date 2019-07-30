package es.jarroyo.revolut.ui.viewmodel.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListRequest
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListUseCase
import es.jarroyo.revolut.domain.usecase.currency.getFavouriteCurrency.GetFavouriteCurrencyUseCase
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyRequest
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyUseCase
import es.jarroyo.revolut.utils.launchSilent
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CurrencyViewModel
    @Inject
    constructor(private val getCurrencyListUseCase: GetCurrencyListUseCase,
                private val getFavouriteCurrencyUseCase: GetFavouriteCurrencyUseCase,
                private val insertFavouriteCurrencyUseCase: InsertFavouriteCurrencyUseCase,
                private val coroutineContext: CoroutineContext)
    : ViewModel() {

    private var job: Job = Job()

    var getCurrencyListStateLiveData = MutableLiveData<GetCurrencyListState>()
    var getFavouriteCurrencyLiveData = MutableLiveData<GetFavouriteCurrencyState>()

    init {
    }

    /**
     * GET CURRENCY LIST
     */
    fun getCurrencyList(currency: Currency) = launchSilent(coroutineContext, job) {
        getCurrencyListStateLiveData.postValue(LoadingGetCurrencyListState())

        val request = GetCurrencyListRequest(currency)
        val response = getCurrencyListUseCase.execute(request)
        processCurrencyListResponse(response)
    }

    private fun processCurrencyListResponse(response: Response<CurrencyListResponse>){
        if (response is Response.Success) {
            getCurrencyListStateLiveData.postValue(
                SuccessGetCurrencyListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getCurrencyListStateLiveData.postValue(
                ErrorGetCurrencyListState(
                    response
                )
            )
        }
    }

    /**
     * GET FAVOURITE CURRENCY
     */
    fun getFavouriteCurrency() = launchSilent(coroutineContext, job) {
        getFavouriteCurrencyLiveData.postValue(LoadingGetFavouriteCurrencyState())

        val response = getFavouriteCurrencyUseCase.execute()
        processFavouriteCurrencyResponse(response)
    }

    fun processFavouriteCurrencyResponse(response: Response<Currency>){
        if (response is Response.Success) {
            getFavouriteCurrencyLiveData.postValue(
                SuccessGetFavouriteCurrencyState(
                    response
                )
            )
        } else if (response is Response.Error) {
            getFavouriteCurrencyLiveData.postValue(
                ErrorGetFavouriteCurrencyState(
                    response
                )
            )
        }
    }

    /**
     * INSERT FAVOURITE CURRENCY
     */
    fun insertFavouriteCurrency(currency: Currency) = launchSilent(coroutineContext, job) {
        val request = InsertFavouriteCurrencyRequest(currency)
        val response = insertFavouriteCurrencyUseCase.execute(request)
        processFavouriteCurrencyResponse(response)
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}