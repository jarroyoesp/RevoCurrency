package es.jarroyo.revolut.app.di.component

import dagger.Component
import es.jarroyo.revolut.app.di.module.*
import es.jarroyo.revolut.app.di.subcomponent.currencyList.activity.CurrencyListActivityComponent
import es.jarroyo.revolut.app.di.subcomponent.currencyList.activity.CurrencyListActivityModule
import es.jarroyo.revolut.app.di.subcomponent.currencyList.fragment.CurrencyListFragmentComponent
import es.jarroyo.revolut.app.di.subcomponent.currencyList.fragment.CurrencyListFragmentModule
import es.jarroyo.revolut.app.di.viewmodel.ViewModelFactoryModule
import es.jarroyo.revolut.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        UtilsModule::class,
        RepositoryModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    )
)
interface ApplicationComponent {
    /**
     * UI - ACTIVITY
     */

    /**
     * CURRENCY LIST
     */
    fun plus(module: CurrencyListActivityModule): CurrencyListActivityComponent
    fun plus(module: CurrencyListFragmentModule): CurrencyListFragmentComponent

}