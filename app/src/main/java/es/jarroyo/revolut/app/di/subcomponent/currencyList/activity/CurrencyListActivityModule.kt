package es.jarroyo.revolut.app.di.subcomponent.currencyList.activity

import dagger.Module
import es.jarroyo.revolut.app.di.module.ActivityModule
import es.jarroyo.revolut.ui.currencyList.activity.CurrencyListActivity

@Module
class CurrencyListActivityModule(activity: CurrencyListActivity) : ActivityModule(activity)