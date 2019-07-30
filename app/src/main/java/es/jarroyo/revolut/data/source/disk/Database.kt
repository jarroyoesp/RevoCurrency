package es.jarroyo.revolut.data.source.disk

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import es.jarroyo.revolut.data.entity.CurrencyEntity
import es.jarroyo.revolut.data.source.disk.dao.CurrencyDao

@Database(entities = [CurrencyEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun mCurrencyDao(): CurrencyDao

    companion object {
        private const val DATABASE_NAME: String = "RevolutCurrency_db"

        @JvmField
        val MIGRATION_1_2 = Migration1To2()

        fun createInstance(appContext: Application):
                es.jarroyo.revolut.data.source.disk.Database = Room.databaseBuilder(
            appContext,
            es.jarroyo.revolut.data.source.disk.Database::class.java, DATABASE_NAME
        ).addMigrations(MIGRATION_1_2).build()
    }

    class Migration1To2 : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            Log.d("MIGRATION_DB", "Migration1To2")

            database.execSQL("ALTER TABLE 'CURENCY_ENTITY' ADD COLUMN 'currencyText' TEXT ")

        }
    }

}
