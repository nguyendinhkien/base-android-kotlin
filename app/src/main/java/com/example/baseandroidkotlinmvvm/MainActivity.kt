package com.example.baseandroidkotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseandroidkotlinmvvm.databinding.ActivityMainBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}