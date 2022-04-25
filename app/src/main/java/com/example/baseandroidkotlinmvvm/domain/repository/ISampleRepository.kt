package com.example.baseandroidkotlinmvvm.domain.repository

import com.example.baseandroidkotlinmvvm.domain.model.SimpleResponse
import io.reactivex.rxjava3.core.Single

interface ISampleRepository {
    suspend fun getSample(): Single<SimpleResponse>
}