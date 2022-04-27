package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import androidx.lifecycle.ViewModel
import com.example.baseandroidkotlinmvvm.domain.use_case.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
): ViewModel() {
}