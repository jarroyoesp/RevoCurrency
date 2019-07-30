package es.jarroyo.revolut.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.whenever
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.domain.model.currency.Rates
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListRequest
import es.jarroyo.revolut.domain.usecase.currency.getCurrencyList.GetCurrencyListUseCase
import es.jarroyo.revolut.domain.usecase.currency.getFavouriteCurrency.GetFavouriteCurrencyUseCase
import es.jarroyo.revolut.domain.usecase.currency.insertFavouriteCurrency.InsertFavouriteCurrencyUseCase
import es.jarroyo.revolut.ui.viewmodel.currency.CurrencyViewModel
import es.jarroyo.revolut.ui.viewmodel.currency.ErrorGetCurrencyListState
import es.jarroyo.revolut.ui.viewmodel.currency.SuccessGetCurrencyListState
import es.jarroyo.revolut.ui.viewmodel.currency.SuccessGetFavouriteCurrencyState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext



class CurrencyViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit  var viewModel : CurrencyViewModel

    var coroutineContext: CoroutineContext = Dispatchers.Unconfined

    @Mock
    lateinit var getCurrencyListUseCase: GetCurrencyListUseCase

    @Mock
    lateinit var getFavouriteCurrencyUseCase: GetFavouriteCurrencyUseCase

    @Mock
    lateinit var insertFavouriteCurrencyUseCase: InsertFavouriteCurrencyUseCase

    @Mock
    lateinit var lifeCycleOwner: LifecycleOwner

    lateinit var lifeCycle: LifecycleRegistry

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        prepareViewModel()

        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        `when` (lifeCycleOwner.lifecycle).thenReturn(lifeCycle)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    }

    /**
     * Verify that "getBookListUseCase" is executed once when call getCityCurrentWeather()
     */
    @Test
    fun `should request currencyList when call getCurrencyList()`() {
        runBlocking {
            val response = Response.Success(createResponse())
            val request = GetCurrencyListRequest(Currency("EUR", currencyText = "Euro"))
            whenever(getCurrencyListUseCase.execute(request)).thenReturn(response)

            viewModel.getCurrencyList(Currency("EUR"))
            Mockito.verify(getCurrencyListUseCase, Mockito.times(1)).execute()
        }
    }

    /**
     * Verify Success State is set when make request to get data
     */
    @Test
    fun `should success state when finish request`() {
        runBlocking {
            val response = Response.Success(createResponse())
            val request = GetCurrencyListRequest(Currency("EUR", currencyText = "Euro"))
            whenever(getCurrencyListUseCase.execute(request)).thenReturn(response)

            viewModel.getCurrencyList(Currency("EUR"))

            assertThat(viewModel.getCurrencyListStateLiveData.value, instanceOf(SuccessGetCurrencyListState::class.java))
        }
    }


    /**
     * Verify ERROR State is set when make request to get data and NO internet
     */
    @Test
    fun `should error state when no internet`() {
        runBlocking {
            val response = Response.Error(IllegalAccessException())
            val request = GetCurrencyListRequest(Currency("EUR", flagDrawable = Rates.getFlag("EUR"),currencyText = "Euro"))
            whenever(getCurrencyListUseCase.execute(request)).thenReturn(response)

            viewModel.getCurrencyList(Currency("EUR"))

            assertThat(viewModel.getCurrencyListStateLiveData.value, instanceOf(ErrorGetCurrencyListState::class.java))
        }
    }

    /**
     * Verify Success state is set when make request to get favourite currency
     */
    @Test
    fun `should success state when getFavouriteCurrency is called`() {
        runBlocking {
            val response = Response.Success(Currency("EUR"))
            whenever(getFavouriteCurrencyUseCase.execute()).thenReturn(response)

            viewModel.getFavouriteCurrency()

            assertThat(viewModel.getFavouriteCurrencyLiveData.value, instanceOf(SuccessGetFavouriteCurrencyState::class.java))
        }
    }


    private fun prepareViewModel(){
        viewModel = CurrencyViewModel(getCurrencyListUseCase, getFavouriteCurrencyUseCase, insertFavouriteCurrencyUseCase, coroutineContext)
    }


    fun createResponse(): CurrencyListResponse {
        val gson = Gson()
        val json = response
        return gson.fromJson(json, CurrencyListResponse::class.java)
    }

    val response = "{\n" +
            "\t\"base\": \"EUR\",\n" +
            "\t\"date\": \"2018-09-06\",\n" +
            "\t\"rates\": {\n" +
            "\t\t\"AUD\": 1.617,\n" +
            "\t\t\"BGN\": 1.9566,\n" +
            "\t\t\"BRL\": 4.7937,\n" +
            "\t\t\"CAD\": 1.5344,\n" +
            "\t\t\"CHF\": 1.128,\n" +
            "\t\t\"CNY\": 7.9483,\n" +
            "\t\t\"CZK\": 25.725,\n" +
            "\t\t\"DKK\": 7.4597,\n" +
            "\t\t\"GBP\": 0.8986,\n" +
            "\t\t\"HKD\": 9.1361,\n" +
            "\t\t\"HRK\": 7.4371,\n" +
            "\t\t\"HUF\": 326.62,\n" +
            "\t\t\"IDR\": 17330.0,\n" +
            "\t\t\"ILS\": 4.1723,\n" +
            "\t\t\"INR\": 83.751,\n" +
            "\t\t\"ISK\": 127.85,\n" +
            "\t\t\"JPY\": 129.6,\n" +
            "\t\t\"KRW\": 1305.3,\n" +
            "\t\t\"MXN\": 22.374,\n" +
            "\t\t\"MYR\": 4.8139,\n" +
            "\t\t\"NOK\": 9.7799,\n" +
            "\t\t\"NZD\": 1.764,\n" +
            "\t\t\"PHP\": 62.617,\n" +
            "\t\t\"PLN\": 4.32,\n" +
            "\t\t\"RON\": 4.6404,\n" +
            "\t\t\"RUB\": 79.607,\n" +
            "\t\t\"SEK\": 10.595,\n" +
            "\t\t\"SGD\": 1.6006,\n" +
            "\t\t\"THB\": 38.145,\n" +
            "\t\t\"TRY\": 7.6313,\n" +
            "\t\t\"USD\": 1.1639,\n" +
            "\t\t\"ZAR\": 17.83\n" +
            "\t}\n" +
            "}"

}