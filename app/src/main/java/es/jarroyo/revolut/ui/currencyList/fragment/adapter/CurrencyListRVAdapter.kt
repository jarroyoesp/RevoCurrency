package es.jarroyo.revolut.ui.currencyList.fragment.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.jarroyo.revolut.R
import es.jarroyo.revolut.domain.model.currency.Currency
import es.jarroyo.revolut.ui.base.loadRoundedUrl
import kotlinx.android.synthetic.main.item_rv_currency.view.*

val TYPE_BASE = 0
val TYPE_CURRENCY = 1

class CurrencyListRVAdapter(
    private var mCurrencyList: MutableList<Currency>? = mutableListOf(),
    private val listenerCurrencyClicked: (ItemCurrency) -> Unit,
    private val listenerAmountChanged: (ItemAmount) -> Unit,
    private var mAmount: Double = 1.0
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        if (mCurrencyList != null) {
            return mCurrencyList?.size!!
        }
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_BASE
        } else {
            return TYPE_CURRENCY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_BASE -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_currency, parent, false)
                return BaseViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rv_currency, parent, false)
                return CurrencyViewHolder(itemView)
            }
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_BASE -> {
                val mcurrency = mCurrencyList?.get(position)
                val baseHolder = holder as BaseViewHolder
                baseHolder.bind(
                    mcurrency!!,
                    mCurrencyList!!,
                    mAmount,
                    listenerAmountChanged
                )
            }
            else -> {
                val mcurrency = mCurrencyList?.get(position)
                val currencyHolder = holder as CurrencyViewHolder
                currencyHolder.bind(
                    mcurrency!!,
                    position,
                    listenerCurrencyClicked,
                    mCurrencyList!!,
                    mAmount
                )
            }
        }

    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            currency: Currency,
            currencyList: MutableList<Currency>,
            amount: Double,
            listenerAmount: (ItemAmount) -> Unit
        ) = with(itemView) {

            // TITLE
            item_rv_currency_tv_name.text = currency.currencyName
            item_rv_currency_et_amount.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            if (item_rv_currency_et_amount.text.isNullOrEmpty()) {
                item_rv_currency_et_amount.setText(amount.toString())
            }
            item_rv_currency_et_amount.isEnabled = true
            item_rv_currency_iv_flag.loadRoundedUrl(context, currency.flagDrawable)

            item_rv_currency_et_amount.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    var amount = 1.0
                    try {
                        amount = text.toString().toDouble()
                        listenerAmount(ItemAmount(amount, currencyList))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            currency: Currency,
            position: Int,
            listener: (ItemCurrency) -> Unit,
            currencyList: MutableList<Currency>,
            amount: Double
        ) = with(itemView) {

            // TITLE
            item_rv_currency_tv_name.text = currency.currencyName
            item_rv_currency_et_amount.setTextColor(ContextCompat.getColor(context, R.color.gray))
            item_rv_currency_iv_flag.loadRoundedUrl(context, currency.flagDrawable)

            item_rv_currency_et_amount.isEnabled = false

            var number3digits: Double = 0.0
            if (amount != null && currency.value != null) {
                val value = amount * currency.value
                number3digits = Math.round(value * 10000.0) / 10000.0
            }
            item_rv_currency_et_amount.setText(number3digits.toString())


            setOnClickListener {
                item_rv_currency_et_amount.setText("1.0")
                listener(ItemCurrency(position, currency, this, currencyList))
            }
        }

    }

    fun setCurrencyList(currencyList: MutableList<Currency>?) {
        mCurrencyList = currencyList
    }

    fun setAmount(amount: Double) {
        mAmount = amount
    }
}

data class ItemCurrency(val position: Int, val currency: Currency, val itemView: View, var currencyList: MutableList<Currency>)
data class ItemAmount(val amount: Double, var currencyList: MutableList<Currency>)