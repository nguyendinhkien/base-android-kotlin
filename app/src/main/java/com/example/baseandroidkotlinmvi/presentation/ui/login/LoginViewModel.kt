package com.example.baseandroidkotlinmvi.presentation.ui.login

import androidx.lifecycle.viewModelScope
import com.example.baseandroidkotlinmvi.presentation.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun requestLogin(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginViewState.LoadingState(true)
            delay(3000)
            _uiState.value = LoginViewState.LoadingState(false)
            _uiState.value = LoginViewState.SuccessState(true)
        }
    }

    private fun checkValidInput(username: String, password: String): Boolean {
//        if (username.isBlank()) {
//            _uiState.update {
//                state->
//                LoginViewState.InvalidInputState(
//                    invalidUsername = "username is required",
//                    invalidPassword = state as
//                )
//            }
//        }
        return true
    }

    private val _uiState = MutableStateFlow<LoginViewState>(LoginViewState.InitState)
    val uiState = _uiState.asStateFlow()

}

sealed class LoginViewState {
    object InitState : LoginViewState()
    data class LoadingState(val isShowLoading: Boolean) : LoginViewState()
    data class FailureState(val error: Throwable) : LoginViewState()
    data class InvalidInputState(val invalidUsername: String, val invalidPassword: String) :
        LoginViewState()

    data class SuccessState(val isSuccessful: Boolean) : LoginViewState()
}