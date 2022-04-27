package com.example.baseandroidkotlinmvvm.presentation.ui.sample_ui

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.baseandroidkotlinmvvm.R
import com.example.baseandroidkotlinmvvm.databinding.FragmentSampleBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseFragment

class SampleFragment : BaseFragment<FragmentSampleBinding>(FragmentSampleBinding::inflate) {
    private val viewModel: SampleViewModel by navGraphViewModels(R.id.nav_graph)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}