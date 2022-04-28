package com.example.baseandroidkotlinmvvm.data.source.remote

import com.example.baseandroidkotlinmvvm.domain.model.SimpleResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ISampleApi {
    @GET("echo")
    fun getSample():Single<SimpleResponse>
}