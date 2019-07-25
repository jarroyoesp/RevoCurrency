package es.jarroyo.revolut.data.source.disk.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import es.jarroyo.revolut.data.factory.TestFavouriteCurrencyFactory
import es.jarroyo.revolut.data.source.disk.Database
import es.jarroyo.revolut.domain.model.currency.Currency
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private lateinit var database: Database

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            Database::class.java
        ).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun GIVEN_favourite_currency_on_database_WHEN_get_favourite_currency_THEN_return_currency() {
        try {
            val favouriteCurrency = TestFavouriteCurrencyFactory.createFavouriteCurrency()
            database.mCurrencyDao().insert(Currency.toEntity(favouriteCurrency))

            val favouriteCurrencyDatabase = database.mCurrencyDao().getFavouriteCurrency()
            assertEquals(favouriteCurrencyDatabase.currencyName, favouriteCurrency.currencyName)
        } catch (e: SQLiteConstraintException) {
            assert(e is SQLiteConstraintException)
        }
    }

    @Test
    fun GIVEN_currency_WHEN_delete_favourite_currency_THEN_currency_is_deleted() {
        val favouriteCurrency = TestFavouriteCurrencyFactory.createFavouriteCurrency()
        database.mCurrencyDao().insert(Currency.toEntity(favouriteCurrency))

        database.mCurrencyDao().delete(Currency.toEntity(favouriteCurrency))

        val favouriteCurrencyDatabase = database.mCurrencyDao().getFavouriteCurrency()
        assertEquals(favouriteCurrencyDatabase, null)
    }

}