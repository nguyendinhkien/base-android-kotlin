package com.example.baseandroidkotlinmvi.domain.repository

import com.example.baseandroidkotlinmvi.domain.model.SampleModel
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseState
import kotlinx.coroutines.flow.Flow

interface ISampleRepository {
    suspend fun getSample(): Flow<BaseState<List<SampleModel>>>
}