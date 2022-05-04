package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.use_case.SampleUseCase
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseState
import com.example.baseandroidkotlinmvvm.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SampleState.DataState(emptyList()))
    val uiState: StateFlow<SampleState> = _uiState

    init {
        viewModelScope.launch {
//            _uiState.value = SampleState.LoadingState()
            sampleUseCase()
                .collect {
                    _uiState.value = SampleState.DataState(it)
                }
        }
    }
}

sealed class SampleState {
    object LoadingState : SampleState()
    data class ErrorState(var error: Throwable) : SampleState()
    data class DataState(var data: List<SampleModel>) : SampleState()
}