package es.jarroyo.revolut.app.di.subcomponent.currencyList.activity

import dagger.Subcomponent
import es.jarroyo.revolut.ui.currencyList.activity.CurrencyListActivity

@Subcomponent(modules = [CurrencyListActivityModule::class])
interface CurrencyListActivityComponent {
    fun injectTo(activity: CurrencyListActivity)
}