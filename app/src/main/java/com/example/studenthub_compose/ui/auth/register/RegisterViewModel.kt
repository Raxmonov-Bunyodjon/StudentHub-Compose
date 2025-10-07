package com.example.studenthub_compose.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studenthub_compose.domain.model.User
import com.example.studenthub_compose.domain.repository.UserRepository
import com.example.studenthub_compose.ui.auth.login.LoginEvent
import com.example.studenthub_compose.ui.auth.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val uiState: MutableStateFlow<RegisterUiState> = MutableStateFlow(RegisterUiState())
    val event: MutableSharedFlow<RegisterEvent> = MutableSharedFlow()

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            //todo check database by repository
            val existingUser = userRepository.getUserByUsername(uiState.value.userName)
            if (existingUser != null) {
                uiState.update {
                    it.copy(
                        successError = true
                    )
                }
                event.emit(RegisterEvent.UsernameAlreadyExists)
                return@launch
            }

            val newUser = User(
                id = 0,
                firstName = uiState.value.firstName,
                lastName = uiState.value.lastName,
                username = uiState.value.userName,
                password = uiState.value.password
            )

            userRepository.insertUser(newUser)
            event.emit(RegisterEvent.SuccessRegister)
        }
    }

    fun onFirstNameChanged(value: String) {
        uiState.update {
            it.copy(
                firstName = value,
                firstNameError = if (value.isBlank()) "First name is required" else null
            )
        }
        checkButtonState()
    }

    fun onLastNameChanged(value: String) {
        uiState.update {
            it.copy(
                lastName = value,
                lastNameError = if (value.isBlank()) "Last name is required" else null
            )
        }
        checkButtonState()
    }

    fun onUserNameChanged(value: String) {
        uiState.update {
            it.copy(
                userName = value,
                usernameError = if (value.isBlank()) "Username cannot be empty" else null,
                successError = false
            )
        }
        checkButtonState()
    }

    fun onPasswordChanged(newPassword: String) {
        val error = when {
            newPassword.isBlank() -> "Password is required"
            newPassword.length < 8 -> "Password must be at least 8 characters"
            else -> null
        }
        uiState.update {
            it.copy(
                password = newPassword,
                passwordError = error
            )
        }
        checkButtonState()
    }

    private fun checkButtonState() {
        val state = uiState.value
        val enabled = state.firstNameError == null &&
                state.lastNameError == null &&
                state.usernameError == null &&
                state.passwordError == null &&
                state.firstName.isNotBlank() &&
                state.lastName.isNotBlank() &&
                state.userName.isNotBlank() &&
                state.password.isNotBlank()

        uiState.update { it.copy(enableRegisterButton = enabled) }
    }

    fun onVisibilityButtonClicked() {
        uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    sealed class RegisterEvent {
        object SuccessRegister : RegisterEvent()
        object SuccessLogin : RegisterEvent()
        object UsernameAlreadyExists : RegisterEvent()
    }


}

