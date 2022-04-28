package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.example.baseandroidkotlinmvvm.R
import com.example.baseandroidkotlinmvvm.databinding.FragmentSampleBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment : BaseFragment<FragmentSampleBinding>(FragmentSampleBinding::inflate) {
    private val viewModel: SampleViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {
        viewModel.initTest()
    }
}