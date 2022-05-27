package com.example.baseandroidkotlinmvvm.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseandroidkotlinmvvm.databinding.FragmentLoginBinding
import com.example.baseandroidkotlinmvvm.presentation.base.ui.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {
        binding.apply {

            buttonLogin.setOnClickListener {
                viewModel.requestLogin(edtUsername.text.toString(), edtPassword.text.toString())
            }

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is LoginViewState.SuccessState -> {

                        }
                        is LoginViewState.FailureState -> {
                            showError(state.error.message ?: "")
                        }
                        is LoginViewState.LoadingState -> {
                            showLoading(state.isShowLoading)
                        }
                        is LoginViewState.InvalidInputState -> {
                            if (state.invalidUsername.isNotBlank()) {
                                edtUsername.error = state.invalidUsername
                            }
                            if (state.invalidPassword.isNotBlank()) {
                                edtPassword.error = state.invalidPassword
                            }
                        }
                    }
                }
            }
        }
    }
}