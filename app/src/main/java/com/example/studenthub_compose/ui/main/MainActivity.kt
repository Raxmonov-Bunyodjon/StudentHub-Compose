package com.example.studenthub_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.studenthub_compose.domain.repository.UserRepository
import com.example.studenthub_compose.ui.auth.login.LoginNavRoute
import com.example.studenthub_compose.ui.home.HomeNavRoute
import com.example.studenthub_compose.ui.navigation.MainNavGraph
import com.example.studenthub_compose.ui.splash.SplashNavRoute
import com.example.studenthub_compose.ui.splash.SplashScreen
import com.example.studenthub_compose.ui.theme.StudentHubComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val username by userRepository.userUsernameFlow.collectAsState(initial = null)


            val startDestination = if (username != null) {
                HomeNavRoute
            } else {
                LoginNavRoute
            }

            StudentHubComposeTheme {
                MainNavGraph(
                    modifier = Modifier.fillMaxSize(),
                    startDestination = SplashNavRoute.route,
                    userRepository = userRepository
                )
            }
        }
    }
}












