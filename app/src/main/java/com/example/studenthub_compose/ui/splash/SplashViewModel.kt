package com.example.studenthub_compose.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenthub_compose.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        startSplash()
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(1000)
            _uiState.update { it.copy(isLoader = false, isFinished = true) }
        }
    }

    fun isUserSignedIn(): Boolean = userRepository.isUserSignedIn()
}