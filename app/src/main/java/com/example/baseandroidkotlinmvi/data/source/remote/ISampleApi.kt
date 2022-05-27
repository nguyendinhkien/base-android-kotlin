package com.example.baseandroidkotlinmvi.data.source.remote

import com.example.baseandroidkotlinmvi.domain.model.SampleModel
import retrofit2.Response
import retrofit2.http.GET

interface ISampleApi {
    @GET("colors")
    suspend fun getSample(): Response<List<SampleModel>>
}