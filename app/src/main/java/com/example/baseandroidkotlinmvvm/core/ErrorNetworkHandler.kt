package com.example.baseandroidkotlinmvvm.core

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer
import retrofit2.HttpException
import java.io.IOException
import java.net.*

/**
 * Handle Network Exception with Single Transformer
 * all exception will return into Failure
 */
class ErrorNetworkHandler<T : Any> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext { this.handleIfNetworkError(it) }
    }

    private fun handleIfNetworkError(throwable: Throwable): SingleSource<T> {
        if (isNetworkingError(throwable)) return asNetworkError(throwable)
        return Single.error(throwable)
    }

    private fun asNetworkError(throwable: Throwable): Single<T> {
        val failure = failureFrom(throwable)
        return Single.error(failure)
    }
}

private fun failureFrom(throwable: Throwable): Failure {
    return try {
        when {
            isConnectionTimeout(throwable) -> Failure(StatusCode.TIMEOUT, throwable.message ?: "")
            isRequestCanceled(throwable) -> Failure(
                StatusCode.REQUEST_CANCELED,
                throwable.message ?: ""
            )
            noInternetAvailable(throwable) -> Failure(
                StatusCode.NO_CONNECTION,
                throwable.message ?: ""
            )
            isHttpException(throwable) -> ErrorApiHandler.handleErrorOnNext(throwable)
            else -> Failure(StatusCode.UNKNOWN_ERROR, throwable.message ?: "")
        }
    } catch (e: Exception) {
        Failure(StatusCode.UNKNOWN_ERROR, e.message ?: "")
    }
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

fun isHttpException(throwable: Throwable): Boolean {
    return throwable is HttpException
}