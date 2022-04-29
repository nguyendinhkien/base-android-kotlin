package com.example.baseandroidkotlinmvvm.domain.use_case.base

import com.example.baseandroidkotlinmvvm.core.ErrorApiHandler
import com.example.baseandroidkotlinmvvm.core.Failure
import com.example.baseandroidkotlinmvvm.core.StatusCode
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class CallBackWrapper<T> : DisposableSingleObserver<T>() {

    protected abstract fun success(t: T)
    protected abstract fun throwError(t: Throwable)

    override fun onSuccess(t: T) {
        success(t)
    }

    override fun onError(e: Throwable) {
        throwError(
            try {
                when {
                    isConnectionTimeout(e) -> Failure(StatusCode.TIMEOUT, e.message ?: "")
                    isRequestCanceled(e) -> Failure(
                        StatusCode.REQUEST_CANCELED,
                        e.message ?: ""
                    )
                    noInternetAvailable(e) -> Failure(
                        StatusCode.NO_CONNECTION,
                        e.message ?: ""
                    )
                    isHttpException(e) -> ErrorApiHandler.handleErrorOnNext(e)
                    else -> Failure(StatusCode.UNKNOWN_ERROR, e.message ?: "")
                }
            } catch (e: Exception) {
                Failure(StatusCode.UNKNOWN_ERROR, e.message ?: "")
            }
        )

    }

    private fun isNetworkingError(throwable: Throwable): Boolean {
        return isConnectionTimeout(throwable) ||
                noInternetAvailable(throwable) ||
                isRequestCanceled(throwable) ||
                isConnectError(throwable) ||
                isHttpException(throwable)
    }

    private fun isRequestCanceled(throwable: Throwable): Boolean {
        return throwable is IOException && throwable.message == "Canceled"
    }

    private fun noInternetAvailable(throwable: Throwable): Boolean {
        return throwable is UnknownHostException
    }

    private fun isConnectionTimeout(throwable: Throwable): Boolean {
        return throwable is SocketTimeoutException
    }

    private fun isConnectError(throwable: Throwable): Boolean {
        return throwable is ConnectException
    }

    private fun isHttpException(throwable: Throwable): Boolean {
        return throwable is HttpException
    }
}