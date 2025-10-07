package com.example.studenthub_compose.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenthub_compose.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val event: MutableSharedFlow<LoginEvent> = MutableSharedFlow()



    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            //todo check database by repository
            val user = userRepository.getUserByUsername(uiState.value.userName)

            if (user != null && user.password == uiState.value.password) {
                event.emit(LoginEvent.SuccessLogin)
            } else {
                uiState.update { currentState ->
                    currentState.copy(
                        successError = true
                    )
                }
            }
        }
    }

    fun onUserNameChanged(value: String) {
        uiState.update {
            it.copy(
                userName = value
            )
        }
        checkButtonState()
    }

    fun onPasswordChanged(value: String) {
        uiState.update {
            it.copy(
                password = value
            )
        }
        checkButtonState()
    }

    fun checkButtonState() {
        val userName = uiState.value.userName
        val password = uiState.value.password

        val isEnabledButton = userName.isNotEmpty() && password.length >= 8

        uiState.update { currentState ->
            currentState.copy(
                enableLoginButton = isEnabledButton
            )
        }
    }

    fun onVisibilityButtonClicked() {
        uiState.update { currentState ->
            currentState.copy(
                passwordVisible = !currentState.passwordVisible
            )
        }
    }
}