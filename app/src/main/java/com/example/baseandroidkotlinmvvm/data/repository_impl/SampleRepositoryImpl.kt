package com.example.baseandroidkotlinmvvm.data.repository_impl

import com.example.baseandroidkotlinmvvm.data.source.remote.ISampleApi
import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseState
import com.example.baseandroidkotlinmvvm.utils.safeCallApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val api: ISampleApi
) : ISampleRepository {
    override suspend fun getSample() = safeCallApi {
        api.getSample()
    }
}