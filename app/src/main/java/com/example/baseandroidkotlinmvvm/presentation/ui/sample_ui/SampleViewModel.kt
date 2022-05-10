package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidkotlinmvvm.domain.model.SampleModel
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
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

    private val _uiState = MutableStateFlow<SampleState>(SampleState.DataState(emptyList()))
    val uiState: StateFlow<SampleState> = _uiState

    init {
        viewModelScope.launch {
            sampleUseCase()
                .onStart {
                    _uiState.value = SampleState.LoadingState
                }
                .collect { result ->
                    when (result) {
                        is BaseState.Failure -> _uiState.value =
                            SampleState.ErrorState(result.error)
                        is BaseState.Success -> _uiState.value = SampleState.DataState(result.data)
                    }
                }
        }
    }
}

sealed class SampleState {
    object LoadingState : SampleState()
    data class ErrorState(var error: Throwable) : SampleState()
    data class DataState(var data: List<SampleModel>) : SampleState()
}