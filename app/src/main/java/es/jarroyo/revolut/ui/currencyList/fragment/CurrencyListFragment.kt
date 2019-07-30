package es.jarroyo.revolut.ui.currencyList.fragment


import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import es.jarroyo.revolut.app.di.component.ApplicationComponent
import es.jarroyo.revolut.app.di.subcomponent.currencyList.fragment.CurrencyListFragmentModule
import es.jarroyo.revolut.domain.model.Response
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.domain.model.currency.CurrencyListResponse
import es.jarroyo.revolut.ui.base.BaseFragment
import es.jarroyo.revolut.ui.base.snackBar
import es.jarroyo.revolut.ui.currencyList.fragment.adapter.CurrencyListRVAdapter
import es.jarroyo.revolut.ui.currencyList.fragment.adapter.ItemAmount
import es.jarroyo.revolut.ui.currencyList.fragment.adapter.ItemCurrency
import es.jarroyo.revolut.ui.viewmodel.currency.*
import kotlinx.android.synthetic.main.fragment_currency_list.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


const val TIME_BETWEEN_UPDATES = 1000L

class CurrencyListFragment : BaseFragment() {
    override var layoutId = es.jarroyo.revolut.R.layout.fragment_currency_list

    // View model
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mCurrencyViewModel: CurrencyViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: CurrencyListRVAdapter? = null

    // TIMER
    private lateinit var mTimer: Timer

    // Favourite Currency
    lateinit var mFavouriteCurrency : Currency

    private var mErrorGettingInfoFromServer = false

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(CurrencyListFragmentModule(this)).injectTo(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareViewModel()

        configRecyclerView()
        getFavouriteCurrency()
    }

    override fun onResume() {
        super.onResume()
        prepareTaskEverySecond()
    }

    override fun onPause() {
        mTimer.cancel()
        super.onPause()
    }

    private fun prepareTaskEverySecond() {
        mTimer = Timer()
        mTimer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    getFavouriteCurrency()
                }
            },
            0, TIME_BETWEEN_UPDATES)
    }

    private fun prepareViewModel() {
        mCurrencyViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
        observeCurrencyViewModel()
    }

    private fun getFavouriteCurrency() {
        mCurrencyViewModel.getFavouriteCurrency()
    }

    private fun getCurrencyRates(currency: Currency) {
        mCurrencyViewModel.getCurrencyList(currency)
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    private fun observeCurrencyViewModel() {
        mCurrencyViewModel.getCurrencyListStateLiveData.observe(this, mGetGetCurrencyListStateObserver)
        mCurrencyViewModel.getFavouriteCurrencyLiveData.observe(this, mGetGetFavouriteCurrencyStateObserver)
    }

    private val mGetGetCurrencyListStateObserver = Observer<GetCurrencyListState> { state ->
        state?.let {
            when (state) {
                is SuccessGetCurrencyListState -> {
                    hideLoading()
                    hideError()
                    val success = it.response as Response.Success
                    mErrorGettingInfoFromServer = false
                    showCurrencyList(success.data)
                }
                is LoadingGetCurrencyListState -> {
                    showLoading()
                    hideError()
                }
                is ErrorGetCurrencyListState -> {
                    hideLoading()
                    showError((it as ErrorGetCurrencyListState))
                }
            }
        }
    }

    private val mGetGetFavouriteCurrencyStateObserver = Observer<GetFavouriteCurrencyState> { state ->
        state?.let {
            when (state) {
                is SuccessGetFavouriteCurrencyState -> {
                    val success = it.response as Response.Success
                    mFavouriteCurrency = success.data
                    if (!mErrorGettingInfoFromServer) {
                        getCurrencyRates(mFavouriteCurrency)
                    }
                }
                is LoadingGetFavouriteCurrencyState -> {

                }
                is ErrorGetFavouriteCurrencyState -> {

                }
            }
        }
    }


    private fun showLoading(){
        fragment_currency_list_layout_tv_status.text = getString(es.jarroyo.revolut.R.string.updating_data)
    }

    private fun hideLoading(){
    }

    private fun showError(errorGetForecastState: ErrorGetCurrencyListState) {
        fragment_currency_list_layout_no_updated.visibility = View.VISIBLE

        mErrorGettingInfoFromServer = true
        fragment_currency_list_layout_tv_status.text = getString(es.jarroyo.revolut.R.string.error_generic)

        val error = errorGetForecastState.response as Response.Error
        (activity as AppCompatActivity).snackBar(error.exception.message.toString(), onClickListener = View.OnClickListener {
            mErrorGettingInfoFromServer = false
            getFavouriteCurrency()
        }, length = Snackbar.LENGTH_INDEFINITE)
    }

    private fun hideError(){
        fragment_currency_list_layout_no_updated.visibility = View.GONE
    }

    private fun showCurrencyList(currencyListResponse: CurrencyListResponse) {
        if (currencyListResponse == null || currencyListResponse.currencyList.isNullOrEmpty()) {
            fragment_currency_list_swipe_refresh_rv.visibility = View.GONE
            fragment_currency_list_layout_empty.visibility = View.VISIBLE
            fragment_currency_list_layout_tv_status.text = getString(es.jarroyo.revolut.R.string.currency_search_not_found)
        } else {

            //val dateString = DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
            val formatter = SimpleDateFormat("hh:mm:ss.SSS")
            val dateString = formatter.format(Date())

            fragment_currency_list_tv_last_updated.text = Html.fromHtml("Currencies last updated at: <b>$dateString</b>")


            fragment_currency_list_swipe_refresh_rv.visibility = View.VISIBLE
            fragment_currency_list_layout_empty.visibility = View.GONE
            mRvAdapter?.setCurrencyList(currencyListResponse.currencyList)
            updateRatesOnAdapter(currencyListResponse.currencyList)
        }
    }

    private fun updateRatesOnAdapter(currencyList: MutableList<Currency>) {
        for (pos in 1 until currencyList.size) {
            mRvAdapter?.notifyItemChanged(pos)
        }
    }

    /**
     * CONFIG RV VIEW
     */
    fun configRecyclerView() {
        fragment_currency_list_swipe_refresh_rv.isEnabled = false
        (fragment_currency_list_rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL, false
        )
        fragment_currency_list_rv.layoutManager = mLayoutManager

        mRvAdapter = CurrencyListRVAdapter(
            listenerCurrencyClicked = {
                onClickCurrencyItem(it)
            },
            listenerAmountChanged = {
                onAmountBaseItemChanged(it)
            }
        )

        fragment_currency_list_rv.adapter = mRvAdapter
        fragment_currency_list_swipe_refresh_rv.setOnRefreshListener {
            getFavouriteCurrency()
        }
    }

    /**
     * ON CLICK CURRENCY ITEM RV
     */
    private fun onClickCurrencyItem(itemCurrency: ItemCurrency) {
        mCurrencyViewModel.insertFavouriteCurrency(itemCurrency.currency)

        Collections.swap(itemCurrency.currencyList, itemCurrency.position, 0)
        mRvAdapter?.notifyItemMoved(itemCurrency.position, 0)
        mRvAdapter?.notifyItemChanged(0)
        fragment_currency_list_rv.scrollToPosition(0)

        (activity as AppCompatActivity).snackBar("${itemCurrency.currency.currencyName} is now your favourite currency!")

        Handler().postDelayed({
            getFavouriteCurrency()
        }, 500)
    }

    /**
     * ON AMOUNT BASE ITEM CHANGED
     */
    private fun onAmountBaseItemChanged(itemAmount: ItemAmount) {
        mRvAdapter?.setAmount(itemAmount.amount)
        updateRatesOnAdapter(itemAmount.currencyList)
    }
}
