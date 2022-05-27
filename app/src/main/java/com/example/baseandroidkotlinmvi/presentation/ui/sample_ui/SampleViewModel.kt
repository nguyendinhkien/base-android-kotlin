package com.example.baseandroidkotlinmvi.presentation.ui.sample_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidkotlinmvi.domain.model.SampleModel
import com.example.baseandroidkotlinmvi.domain.use_case.SampleUseCase
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SampleState>(SampleState.DataState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            sampleUseCase()
                .onStart {
                    _uiState.value = SampleState.LoadingState(true)
                }
                .collect { result ->
                    _uiState.value = SampleState.LoadingState(false)
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
    data class LoadingState(var isShowLoading: Boolean) : SampleState()
    data class ErrorState(var error: Throwable) : SampleState()
    data class DataState(var data: List<SampleModel>) : SampleState()
}