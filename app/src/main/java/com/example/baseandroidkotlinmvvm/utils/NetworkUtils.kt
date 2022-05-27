package com.example.baseandroidkotlinmvvm.utils


import com.example.baseandroidkotlinmvvm.core.NetworkErrorException
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Utils {
    companion object {
        fun resolveError(e: Throwable): Throwable {
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

            return error
        }
    }
}

suspend fun <T : Any> safeCallApi(
    networkApiCall: suspend () -> Response<T>
): Flow<BaseState<T>> {
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(BaseState.Success(it))
            }
                ?: emit(BaseState.Failure(NetworkErrorException(errorMessage = "Empty response body")))
            return@flow
        }
        emit(
            BaseState.Failure(
                NetworkErrorException(
                    errorCode = response.code(),
                    errorMessage = response.message()
                )
            )
        )
    }.catch { e ->
        emit(BaseState.Failure(Utils.resolveError(e)))
        return@catch
    }.flowOn(Dispatchers.IO)
}