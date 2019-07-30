package es.jarroyo.revolut.ui.currencyList.activity

import es.jarroyo.revolut.R
import es.jarroyo.revolut.app.di.component.ApplicationComponent
import es.jarroyo.revolut.app.di.subcomponent.currencyList.activity.CurrencyListActivityModule
import es.jarroyo.revolut.ui.base.BaseActivity

class CurrencyListActivity : BaseActivity() {

    override var layoutId = R.layout.activity_currency_list

    override fun setupInjection(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(CurrencyListActivityModule(this)).injectTo(this)
    }

}
