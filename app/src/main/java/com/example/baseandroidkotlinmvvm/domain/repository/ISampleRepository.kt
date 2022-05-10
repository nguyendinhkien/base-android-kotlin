package com.example.baseandroidkotlinmvvm.domain.repository

import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.model.SimpleResponse
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseState
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface ISampleRepository {
    suspend fun getSample(): Flow<BaseState<List<SampleModel>>>
}