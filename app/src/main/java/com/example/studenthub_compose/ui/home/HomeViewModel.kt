package com.example.studenthub_compose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenthub_compose.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userRepository.getUserByUsername(uiState.value.username)
                _uiState.update {
                    it.copy(
                        username = user?.username ?: "Guest",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Error loading user", isLoading = false) }
            }
        }
    }

    fun onTabSelected(tab: HomeTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.logout()
            withContext(Dispatchers.Main) {
                onLoggedOut()
            }
        }
    }}

