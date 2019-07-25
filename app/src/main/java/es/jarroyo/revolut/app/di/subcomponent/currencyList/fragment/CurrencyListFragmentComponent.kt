package es.jarroyo.revolut.app.di.subcomponent.currencyList.fragment

import dagger.Subcomponent
import es.jarroyo.revolut.ui.currencyList.fragment.CurrencyListFragment

@Subcomponent(modules = arrayOf(CurrencyListFragmentModule::class))
interface CurrencyListFragmentComponent {
    fun injectTo(fragment: CurrencyListFragment)
}