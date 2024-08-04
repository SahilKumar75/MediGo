package com.example.musical.auth.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musical.auth.data.LoginRequest
import com.example.musical.auth.data.Result
import com.example.musical.auth.data.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
        private set

    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun loginUser() {
        uiState = uiState.copy(isAuthenticating = true)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.loginUser(
                    LoginRequest(
                        uiState.email,
                        uiState.password
                    )
                )
                uiState = when (response) {
                    is Result.Success -> {
                        uiState.copy(
                            isAuthenticating = false,
                            authSuccess = true
                        )
                    }
                    is Result.Error -> {
                        uiState.copy(
                            isAuthenticating = false,
                            errorMessage = response.message
                        )
                    }
                }
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.message)
            } finally {
                uiState = uiState.copy(isAuthenticating = false)
            }
        }
    }
}

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isAuthenticating: Boolean = false,
    val errorMessage: String? = null,
    val authSuccess: Boolean = false,
)
