package com.example.baseandroidkotlinmvvm.domain.use_case.base

import io.reactivex.rxjava3.observers.DisposableSingleObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class CallBackWrapper<T> : DisposableSingleObserver<T>() {

    protected abstract fun success(t: T)
    protected abstract fun throwError(t: Throwable)

    override fun onSuccess(t: T) {
        success(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> {
                val response = e.response()?.errorBody()
                response?.let {
                    throwError(Throwable(message = getErrorMessage(response) ?: e.message()))
                }
            }
            is UnknownHostException -> {
                throwError(Throwable(message = "Network error"))
            }
            else -> {
                throwError(Throwable(e.message))
            }
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String? {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString(("message"))
        } catch (e: Exception) {
            null
        }
    }
}