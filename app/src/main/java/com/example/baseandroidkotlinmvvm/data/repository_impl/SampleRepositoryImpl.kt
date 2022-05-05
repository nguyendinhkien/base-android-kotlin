package com.example.baseandroidkotlinmvvm.data.repository_impl

import com.example.baseandroidkotlinmvvm.data.source.remote.ISampleApi
import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val api: ISampleApi
) : ISampleRepository {
    override suspend fun getSample(): Flow<List<SampleModel>> {
        return flow {
            val response = api.getSample()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(body)
            }
        }
    }
}