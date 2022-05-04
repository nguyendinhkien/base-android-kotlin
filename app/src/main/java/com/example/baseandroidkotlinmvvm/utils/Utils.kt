package com.example.baseandroidkotlinmvvm.utils


import com.example.baseandroidkotlinmvvm.core.NetworkErrorException
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseState
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Utils {
    companion object {
        fun resolveError(e: Exception): BaseState.ErrorState {
            var error = e
            when (e) {
                is SocketTimeoutException -> {
                    error = NetworkErrorException(errorMessage = "Connection error")
                }
                is ConnectException -> {
                    error = NetworkErrorException(errorMessage = "No internet access")
                }
                is UnknownHostException -> {
                    error = NetworkErrorException(errorMessage = "No internet access")
                }
                is HttpException -> {
                    error = when (e.code()) {
                        502 -> {
                            NetworkErrorException(e.code(), " Internal error")
                        }
                        else -> {
                            NetworkErrorException.parseException(e)
                        }
                    }
                }
            }

            return BaseState.ErrorState(error)
        }
    }
}