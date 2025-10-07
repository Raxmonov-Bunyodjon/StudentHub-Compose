package com.example.studenthub_compose.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.studenthub_compose.ui.auth.login.LoginNavRoute
import com.example.studenthub_compose.ui.auth.login.loginScreen
import com.example.studenthub_compose.ui.auth.register.navigateToRegister
import com.example.studenthub_compose.ui.auth.register.registerScreen

@Composable
fun MainNavGraph(
    startDestination: Any = LoginNavRoute,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        loginScreen(
            openRegisterScreen = {
                navController.navigateToRegister()
            },
            openMainScreen = {
//                navController.navigateToMain()
            }
        )
        registerScreen(navController)
    }

}