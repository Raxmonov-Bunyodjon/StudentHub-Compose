package com.example.studenthub_compose.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object HomeNavRoute {
    const val route = "home"
}



//fun NavController.navigateToHome(navOptions: NavOptions? = null) =
//    navigate(HomeNavRoute, navOptions)


fun NavGraphBuilder.homeScreen(
    onLogout: () -> Unit
) {
    composable<HomeNavRoute> {
        HomeRoute(onLogout = onLogout)
    }
}

