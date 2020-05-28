package com.example.retrofit2coroutinescalladapter.data.network.adapter

import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {

    /**
     * Represents success response with body.
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /**
     * Represents failure response (non-2xx) with body.
     */
    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    /**
     *  Represents network failure (such as no internet connection).
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * Represents unexpected exceptions (for example parsing issues).
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}