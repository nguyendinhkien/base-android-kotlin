package com.example.baseandroidkotlinmvi.presentation.ui.navigation_test.screen_a

import android.os.Bundle
import androidx.navigation.navGraphViewModels
import com.example.baseandroidkotlinmvi.R
import com.example.baseandroidkotlinmvi.databinding.FragmentScreenABinding
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenAFragment : BaseFragment<FragmentScreenABinding>(FragmentScreenABinding::inflate) {
    private val viewModel: ScreenAViewModel by navGraphViewModels(R.id.navigation_test)
    override fun onViewReady(savedInstanceState: Bundle?) {

    }

}