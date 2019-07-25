package es.jarroyo.revolut.ui

import es.jarroyo.revolut.app.di.DaggerTestApplicationComponent
import es.jarroyo.revolut.app.di.TestApplicationModule

open class RevolutCurrencyApp : App() {

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        graph = DaggerTestApplicationComponent.builder()
                .testApplicationModule(TestApplicationModule(this))
                .build()
    }
}