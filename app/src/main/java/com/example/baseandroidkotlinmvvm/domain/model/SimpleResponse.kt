package com.example.baseandroidkotlinmvvm.domain.model

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("message")
    var message: String? = null
)