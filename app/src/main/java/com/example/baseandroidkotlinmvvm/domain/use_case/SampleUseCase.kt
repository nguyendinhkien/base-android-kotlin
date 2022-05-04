package com.example.baseandroidkotlinmvvm.domain.use_case

import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val repository: ISampleRepository
) {
    suspend operator fun invoke(): Flow<List<SampleModel>> {
        val response = repository.getSample()
        return response
    }
}