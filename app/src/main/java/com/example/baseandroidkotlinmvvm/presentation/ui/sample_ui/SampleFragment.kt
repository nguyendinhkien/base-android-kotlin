package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseandroidkotlinmvvm.databinding.FragmentSampleBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SampleFragment : BaseFragment<FragmentSampleBinding>(FragmentSampleBinding::inflate) {
    private val viewModel: SampleViewModel by viewModels()

    override fun onViewReady(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.uiState
                .collect {
                    when (it) {
                        is SampleState.DataState -> binding.textSample.text =
                            it.data.map { color -> color.name }.toString()
                        is SampleState.ErrorState -> {
                            showError()
                        }
                        is SampleState.LoadingState -> {
                            showLoading()
                        }
                    }
                }
        }
    }
}