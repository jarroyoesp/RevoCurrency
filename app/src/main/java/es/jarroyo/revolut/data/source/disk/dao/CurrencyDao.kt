package es.jarroyo.revolut.data.source.disk.dao

import androidx.room.*
import es.jarroyo.revolut.data.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyEntity: CurrencyEntity)


    @Delete
    fun delete(currencyEntity: CurrencyEntity)

    @Query("DELETE FROM CURENCY_ENTITY")
    fun deleteAll()

    @Query("SELECT * FROM CURENCY_ENTITY")
    fun getList(): List<CurrencyEntity>

    @Query("SELECT * FROM CURENCY_ENTITY where id == 1")
    fun getFavouriteCurrency(): CurrencyEntity

}