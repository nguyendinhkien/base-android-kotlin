package com.example.baseandroidkotlinmvvm.core

data class Failure(val code: Int, val msg: String) : Throwable()