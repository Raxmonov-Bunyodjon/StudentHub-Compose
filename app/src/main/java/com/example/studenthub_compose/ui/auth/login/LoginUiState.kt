package com.example.studenthub_compose.ui.auth.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val enableLoginButton: Boolean = false,
    val passwordVisible: Boolean = false,
    val successError : Boolean = false,
)

sealed interface LoginEvent {
    object SuccessLogin : LoginEvent
}