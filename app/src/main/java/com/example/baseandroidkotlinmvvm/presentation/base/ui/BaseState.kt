package com.example.baseandroidkotlinmvvm.presentation.base.ui

sealed class BaseState<out T> {
    object LoadingState : BaseState<Nothing>()
    data class ErrorState(var error: Throwable) : BaseState<Nothing>()
    data class DataState<T>(var data: T) : BaseState<T>()
}
