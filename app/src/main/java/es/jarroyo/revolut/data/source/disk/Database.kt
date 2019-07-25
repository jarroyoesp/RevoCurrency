package es.jarroyo.revolut.data.source.disk

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.jarroyo.revolut.data.entity.CurrencyEntity
import es.jarroyo.revolut.data.source.disk.dao.CurrencyDao

@Database(entities = arrayOf(CurrencyEntity::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun mCurrencyDao(): CurrencyDao

    companion object {
        private val DATABASE_NAME: String = "RevolutCurrency_db"

        fun createInstance(appContext: Application):
                es.jarroyo.revolut.data.source.disk.Database = Room.databaseBuilder(
            appContext,
            es.jarroyo.revolut.data.source.disk.Database::class.java, DATABASE_NAME
        ).build()
    }

}
