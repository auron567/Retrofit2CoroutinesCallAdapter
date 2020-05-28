package com.example.retrofit2coroutinescalladapter.data.model

sealed class Result<out T : Any> {

    class Success<out T : Any>(val value: T) : Result<T>()

    class Error(val message: String?) : Result<Nothing>()
}