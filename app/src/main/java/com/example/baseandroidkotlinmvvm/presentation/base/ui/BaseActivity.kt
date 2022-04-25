package com.example.baseandroidkotlinmvvm.presentation.base.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B:ViewBinding>(bindingFactory: (LayoutInflater)-> B):AppCompatActivity() {
    val binding: B by lazy { bindingFactory(layoutInflater) }

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
    }
}