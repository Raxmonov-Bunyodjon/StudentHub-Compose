package com.example.studenthub_compose.ui.auth.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object LoginNavRoute  {
    const val route = "login"
}


fun NavController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(LoginNavRoute, navOptions)

fun NavGraphBuilder.loginScreen(
    openRegisterScreen: () -> Unit = {},
    openMainScreen: () -> Unit = {},
) {
    composable<LoginNavRoute> {
        LoginRoute(
            openRegisterScreen = openRegisterScreen,
            openMainScreen = openMainScreen,
        )
    }
}
