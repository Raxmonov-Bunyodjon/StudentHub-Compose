package com.example.studenthub_compose.ui.main.faculty

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object FacultyNavRoute



fun NavController.navigateToFaculty(navOptions: NavOptions? = null) =
    navigate(FacultyNavRoute, navOptions)


fun NavGraphBuilder.facultyScreen(
    onLogout: () -> Unit
) {
    composable<FacultyNavRoute> {
        FacultyRoute(onLogout = onLogout)
 }
}

