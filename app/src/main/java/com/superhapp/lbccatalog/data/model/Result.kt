package com.superhapp.lbccatalog.data.model

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}