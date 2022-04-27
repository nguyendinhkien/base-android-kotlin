package com.example.baseandroidkotlinmvvm.presentation.base.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onViewReady(savedInstanceState)
    }

    abstract fun onViewReady(savedInstanceState: Bundle?)
}