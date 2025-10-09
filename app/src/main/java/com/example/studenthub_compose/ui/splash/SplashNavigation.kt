package com.example.studenthub_compose.ui.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SplashNavRoute {
    const val route = "splash"
}

fun NavController.navigateToSplash() {
    navigate(SplashNavRoute.route)
}

fun NavGraphBuilder.splashScreen(
    onFinish: (Boolean) -> Unit
){
    composable<SplashNavRoute>{
        SplashRoute(onFinish = onFinish)
    }
}