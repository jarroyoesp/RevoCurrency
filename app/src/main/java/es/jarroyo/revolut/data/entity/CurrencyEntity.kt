package es.jarroyo.revolut.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.jarroyo.revolut.domain.model.currency.Currency
import java.io.Serializable

@Entity(tableName = "CURENCY_ENTITY")
class CurrencyEntity(
        @PrimaryKey
        var id: Long = 1,
        var currencyName: String = ""): Serializable {

    companion object {
        fun toModel(currencyEntity: CurrencyEntity): Currency {
            return Currency(currencyName = currencyEntity.currencyName)
        }

    }
}