package com.mindgarden.mindgarden.presentation.util.base

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    data class Error(val error: kotlin.Error): UIState<Nothing>()
}