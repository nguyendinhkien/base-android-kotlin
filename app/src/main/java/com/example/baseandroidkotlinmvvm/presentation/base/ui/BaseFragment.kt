package com.example.baseandroidkotlinmvvm.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(bindingFactory: (LayoutInflater) -> B) : Fragment() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

//    var hasNavController: Boolean = true

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        onViewReady(savedInstanceState)
    }

    abstract fun onViewReady(savedInstanceState: Bundle?)

    protected fun <T> observeResultFromDestination(key: String, observeResult: (T) -> Unit): T? {
        var r: T? = null
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) { result ->
                r = result
                observeResult(result)
            }
        return r
    }

    protected fun <T> returnData(key: String, result: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, result)
        /*
        If you’d only like to handle a result only once, you must call remove()
        //this.previousBackStackEntry?.savedStateHandle?.remove(key)
        */

    }

    protected fun showLoading(){
        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
    }

    protected fun showError(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}