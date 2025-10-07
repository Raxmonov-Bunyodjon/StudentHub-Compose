package com.example.studenthub_compose.ui.auth.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studenthub_compose.ui.auth.login.navigateToLogin
import kotlinx.serialization.Serializable

@Serializable
object RegisterNavRoute


fun NavController.navigateToRegister(navOptions: NavOptions? = null) =
    navigate(RegisterNavRoute, navOptions)

fun NavGraphBuilder.registerScreen(
    navController: NavController
){
    composable<RegisterNavRoute> {
        RegisterRoute(
            openLoginScreen = { navController.navigateToLogin() }
        )
    }
}
