package com.example.baseandroidkotlinmvi.data.repository_impl

import com.example.baseandroidkotlinmvi.data.source.remote.ISampleApi
import com.example.baseandroidkotlinmvi.domain.repository.ISampleRepository
import com.example.baseandroidkotlinmvi.utils.safeCallApi
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val api: ISampleApi
) : ISampleRepository {
    override suspend fun getSample() = safeCallApi {
        api.getSample()
    }
}