package com.example.baseandroidkotlinmvi.presentation.base.ui

sealed class BaseState<out T> {
    data class Failure(var error: Throwable) : BaseState<Nothing>()
    data class Success<T>(var data: T) : BaseState<T>()
}
