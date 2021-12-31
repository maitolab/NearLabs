package org.builds.nearlabs.common

import java.io.IOException

sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class GenericError(val throwable: Throwable? = null) : ResultWrapper<Nothing>()

    object None : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()

    @Throws(Exception::class)
    fun takeValueOrThrow(): T {
        return when (this) {
            is Success -> value
            is GenericError -> throw throwable ?: Throwable()
            else -> throw Throwable("Unknown the result type")
        }
    }
}