package com.example.baseandroidkotlinmvi

import android.os.Bundle
import com.example.baseandroidkotlinmvi.databinding.ActivityMainBinding
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun onViewReady(savedInstanceState: Bundle?) {
    }
}