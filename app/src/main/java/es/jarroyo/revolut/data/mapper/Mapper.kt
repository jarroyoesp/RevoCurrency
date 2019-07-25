package es.jarroyo.revolut.data.mapper

import es.jarroyo.revolut.data.exception.MapperException


internal interface Mapper<in I, out O> {

    @Throws(MapperException::class)
    fun map(input: I): O
}
