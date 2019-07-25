package es.jarroyo.revolut.data.source.cache

import es.jarroyo.revolut.domain.model.currency.Currency


object CacheDataSource {
    var mFavouriteCurrency: Currency? = null

    fun clearCache() {
        mFavouriteCurrency = null
    }
}