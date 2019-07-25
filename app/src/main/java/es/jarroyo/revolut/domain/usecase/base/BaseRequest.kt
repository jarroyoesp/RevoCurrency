package es.jarroyo.revolut.domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
