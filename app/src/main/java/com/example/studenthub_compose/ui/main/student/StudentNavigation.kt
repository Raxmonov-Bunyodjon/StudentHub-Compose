package com.example.studenthub_compose.ui.main.student

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object StudentNavRoute



fun NavController.navigateToStudent(navOptions: NavOptions? = null) =
    navigate(StudentNavRoute, navOptions)


fun NavGraphBuilder.studentScreen() {
    composable<StudentNavRoute> {
        StudentRoute()
    }
}
