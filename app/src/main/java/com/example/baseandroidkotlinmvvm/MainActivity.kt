package com.example.baseandroidkotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseandroidkotlinmvvm.databinding.ActivityMainBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onViewReady(savedInstanceState: Bundle?) {
    }
}