package com.mindgarden.mindgarden.util

sealed class Result<out R>() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMsg: String?, val throwable: Throwable) : Result<Nothing>()
}