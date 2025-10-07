package com.example.studenthub_compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.studenthub_compose.ui.auth.login.LoginNavRoute
import com.example.studenthub_compose.ui.navigation.MainNavGraph
import com.example.studenthub_compose.ui.theme.StudentHubComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            StudentHubComposeTheme(false) {
                MainNavGraph(
                    modifier = Modifier.fillMaxSize(),
                    startDestination = LoginNavRoute,
                )
            }
        }
    }
}












