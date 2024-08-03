package com.example.musical.auth.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musical.auth.data.RegisterRequest
import com.example.musical.auth.data.Result
import com.example.musical.auth.data.RetrofitInstance
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())
        private set

    fun onNameChange(newName: String) {
        uiState = uiState.copy(name = newName)
    }

    fun onEmailChange(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }

    fun createUser() {
        uiState = uiState.copy(isAuthenticating = true)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.registerUser(
                    RegisterRequest(
                        uiState.name,
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

data class SignUpUIState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isAuthenticating: Boolean = false,
    val errorMessage: String? = null,
    val authSuccess: Boolean = false,
)
