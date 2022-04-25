package com.example.baseandroidkotlinmvvm.data.repository_impl

import com.example.baseandroidkotlinmvvm.data.source.remote.ISampleApi
import com.example.baseandroidkotlinmvvm.domain.model.SimpleResponse
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val api: ISampleApi
): ISampleRepository {
    override suspend fun getSample(): Single<SimpleResponse> {
        return api.getSample()
    }
}