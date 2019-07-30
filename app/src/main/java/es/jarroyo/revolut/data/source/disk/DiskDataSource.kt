package es.jarroyo.revolut.data.source.disk

import android.app.Application
import android.content.Context
import es.jarroyo.revolut.data.entity.CurrencyEntity

class DiskDataSource(appContext: Context) {

    companion object {
        var database: Database? = null
    }

    init {
        database = Database.createInstance(appContext as Application)
    }

    /***********************************************************************************************
     * CURRENCY ENTITY
     **********************************************************************************************/
    fun insertCurrency(currencyEntity: CurrencyEntity) = database?.mCurrencyDao()?.insert(currencyEntity)
    fun getCurrency() = database?.mCurrencyDao()?.getFavouriteCurrency()
    fun deleteCurrency(currencyEntity: CurrencyEntity) = database?.mCurrencyDao()?.delete(currencyEntity)


    /***********************************************************************************************
     * COMMON
     **********************************************************************************************/
    fun deleteAllTables() {
        database?.mCurrencyDao()?.deleteAll()
    }
}
