package com.mindgarden.mindgarden.data.model

sealed class Result<out R>() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMsg: String) : Result<Nothing>()
}