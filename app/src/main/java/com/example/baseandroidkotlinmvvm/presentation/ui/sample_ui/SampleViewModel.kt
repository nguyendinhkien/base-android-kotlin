package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidkotlinmvvm.domain.use_case.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : ViewModel() {
    fun initTest() {
        viewModelScope.launch {
            println("test usecase")
            sampleUseCase.execute(
                onSuccess = {

                },
                onError = {
                    println("${it.message}")
                },
                onFinished = {

                }
            )
        }
    }

}