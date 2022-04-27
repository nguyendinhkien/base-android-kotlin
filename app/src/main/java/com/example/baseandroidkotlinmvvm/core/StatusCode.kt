package com.example.baseandroidkotlinmvvm.core

object StatusCode {
    // Informational status code : 1xx
    // add informational code here

    // Success Code
    const val SUCCESS = 200
    const val NO_CONTENT = 204

    // Client Error Code
    const val REQUEST_CANCELED = 417

    // Server Error Code
    const val SERVER_ERROR = 500
    const val NO_CONNECTION = 503
    const val TIMEOUT = 504
    const val UNKNOWN_ERROR = 520
}