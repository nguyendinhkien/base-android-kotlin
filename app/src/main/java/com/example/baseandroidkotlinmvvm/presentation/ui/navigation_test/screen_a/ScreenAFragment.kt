package com.example.baseandroidkotlinmvvm.presentation.ui.navigation_test.screen_a

import android.os.Bundle
import androidx.navigation.navGraphViewModels
import com.example.baseandroidkotlinmvvm.R
import com.example.baseandroidkotlinmvvm.databinding.FragmentScreenABinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenAFragment : BaseFragment<FragmentScreenABinding>(FragmentScreenABinding::inflate) {
    private val viewModel: ScreenAViewModel by navGraphViewModels(R.id.navigation_test)
    override fun onViewReady(savedInstanceState: Bundle?) {

    }

}