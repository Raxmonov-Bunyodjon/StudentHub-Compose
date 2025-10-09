package com.example.studenthub_compose.ui.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val username: String = "",
    val currentTab: HomeTab = HomeTab.FACULTY,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class HomeTab {
    FACULTY, STUDENT, PROFILE
}

