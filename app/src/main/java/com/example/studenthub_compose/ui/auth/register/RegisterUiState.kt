package com.example.studenthub_compose.ui.auth.register


import androidx.compose.runtime.Immutable

@Immutable
data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val userName: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,

    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,


    val enableRegisterButton: Boolean = false,
    val successError : Boolean = false,
    val errorMessage: String = ""
)

sealed interface RegisterEvent {
    object SuccessLogin : RegisterEvent
}