package com.example.baseandroidkotlinmvvm.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(bindingFactory: (LayoutInflater) -> B) : Fragment() {

    val binding: B by lazy { bindingFactory(layoutInflater) }

    var hasNavController: Boolean = true

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}