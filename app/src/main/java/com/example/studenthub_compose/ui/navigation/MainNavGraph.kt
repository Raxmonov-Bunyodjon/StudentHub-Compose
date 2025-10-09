package com.example.studenthub_compose.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.studenthub_compose.domain.repository.UserRepository
import com.example.studenthub_compose.ui.auth.login.LoginNavRoute
import com.example.studenthub_compose.ui.auth.login.loginScreen
import com.example.studenthub_compose.ui.auth.register.navigateToRegister
import com.example.studenthub_compose.ui.auth.register.registerScreen
import com.example.studenthub_compose.ui.home.HomeNavRoute
import com.example.studenthub_compose.ui.home.homeScreen
import com.example.studenthub_compose.ui.splash.SplashNavRoute
import com.example.studenthub_compose.ui.splash.SplashScreen
import com.example.studenthub_compose.ui.splash.splashScreen
import kotlinx.coroutines.launch


@Composable
fun MainNavGraph(
    startDestination: Any = SplashNavRoute,
    userRepository: UserRepository,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = SplashNavRoute,
        modifier = modifier,
    ) {

        splashScreen(
            onFinish = {
                val nextRoute = if (userRepository.isUserSignedIn()) {
                    HomeNavRoute
                } else {
                    LoginNavRoute
                }
                navController.navigate(nextRoute) {
                    popUpTo(SplashNavRoute) { inclusive = true }
                }
            }
        )

        loginScreen(
            openRegisterScreen = {
                navController.navigateToRegister()
            },
            openMainScreen = {
                navController.navigate(HomeNavRoute) {
                    popUpTo(LoginNavRoute) { inclusive = true }
                }
            }
        )

        registerScreen(navController)

        homeScreen(
            onLogout = {
                scope.launch {
                    userRepository.logout()
                    navController.navigate(LoginNavRoute) {
                        popUpTo(HomeNavRoute) { inclusive = true }
                    }
                }
            }
        )
    }
}