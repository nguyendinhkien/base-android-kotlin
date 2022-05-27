package com.example.baseandroidkotlinmvi.presentation.ui.sample_ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseandroidkotlinmvi.databinding.FragmentSampleBinding
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment : BaseFragment<FragmentSampleBinding>(FragmentSampleBinding::inflate) {
    private val viewModel: SampleViewModel by viewModels()

    override fun onViewReady(savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when (state) {
                    is SampleState.DataState -> binding.textSample.text =
                        state.data.map { color -> color.name }.toString()
                    is SampleState.ErrorState -> {
                        showError(state.error.message ?: "")
                    }
                    is SampleState.LoadingState -> {
                        showLoading(state.isShowLoading)
                    }
                }
            }
        }
    }
}