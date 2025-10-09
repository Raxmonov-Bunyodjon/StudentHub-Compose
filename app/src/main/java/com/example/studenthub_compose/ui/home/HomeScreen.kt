package com.example.studenthub_compose.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.studenthub_compose.ui.main.faculty.FacultyNavRoute
import com.example.studenthub_compose.ui.main.faculty.facultyScreen
import com.example.studenthub_compose.ui.main.faculty.navigateToFaculty
import com.example.studenthub_compose.ui.main.profile.navigateToProfile
import com.example.studenthub_compose.ui.main.profile.profileScreen
import com.example.studenthub_compose.ui.main.student.navigateToStudent
import com.example.studenthub_compose.ui.main.student.studentScreen
import com.example.studenthub_compose.ui.theme.PrimaryColor
import com.example.studenthub_compose.ui.theme.StudentHubComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onLogout: () -> Unit = {}
) {
    val uiState = viewModel.uiState.collectAsState().value
    val scope = rememberCoroutineScope()

    var showLogoutDialog by remember { mutableStateOf(false) }
    HomeScreen(
        username = uiState.username.ifBlank { "StudentHub" },
        currentTab = uiState.currentTab,
        onTabSelected = viewModel::onTabSelected,
//        onLogout = { viewModel.logout(onLogout) }
        onLogout = onLogout
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    username: String,
    currentTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
    onLogout: () -> Unit,
    enableNavHost: Boolean = true
) {

    SetStatusBarColor()

    val navController = if (enableNavHost) rememberNavController() else null
    val homeNavController = rememberNavController()
    var showLogoutDialog by remember { mutableStateOf(false) }


    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Logout") },
            text = { Text("Do you want to exit?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    onLogout()
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(text = "StudentHub-Compose", color = Color.White) },
                actions = {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PrimaryColor,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = PrimaryColor
            ) {
                NavigationBarItem(
                    selected = currentTab == HomeTab.FACULTY,
                    onClick = {
                        onTabSelected(HomeTab.FACULTY)
                        homeNavController.navigateToFaculty(navOptions {
                            popUpTo(homeNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        })
                    },
                    icon = {
                        Icon(
                            Icons.Default.School,
                            contentDescription = "Faculty",
                            tint = Color.White
                        )
                    },
                    label = { Text("Faculty", color = Color.White) }
                )
                NavigationBarItem(
                    selected = currentTab == HomeTab.STUDENT,
                    onClick = {
                        onTabSelected(HomeTab.STUDENT)
                        homeNavController.navigateToStudent(navOptions {
                            popUpTo(homeNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        })
                    },
                    icon = {
                        Icon(
                            Icons.Default.Group,
                            contentDescription = "Students",
                            tint = Color.White
                        )
                    },
                    label = { Text("Students", color = Color.White) }
                )
                NavigationBarItem(
                    selected = currentTab == HomeTab.PROFILE,
                    onClick = {
                        onTabSelected(HomeTab.PROFILE)
                        homeNavController.navigateToProfile(navOptions {
                            popUpTo(homeNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        })
                    },
                    icon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    },
                    label = { Text("Profile", color = Color.White) }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = homeNavController,
            startDestination = FacultyNavRoute,
        ) {
            facultyScreen(onLogout = {})
            studentScreen()
            profileScreen()
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = PrimaryColor,
            darkIcons = false // oq text va ikonalar uchun false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        username = "StudentHub-Compose",
        currentTab = HomeTab.FACULTY,
        onTabSelected = {},
        onLogout = {},
        enableNavHost = false // 🔹 Preview uchun NavHost o‘chirilgan
    )
}


