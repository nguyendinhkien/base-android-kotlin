package com.example.baseandroidkotlinmvvm.data.source.remote

import com.example.baseandroidkotlinmvvm.core.WrappedResponse
import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import retrofit2.Response
import retrofit2.http.GET

interface ISampleApi {
    @GET("colors")
    suspend fun getSample(): Response<List<SampleModel>>
}