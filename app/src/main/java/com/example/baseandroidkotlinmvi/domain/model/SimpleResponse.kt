package com.example.baseandroidkotlinmvi.domain.model

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("message")
    var message: String? = null
)