package com.example.studenthub_compose.ui.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object ProfileNavRoute



fun NavController.navigateToProfile(navOptions: NavOptions? = null) =
    navigate(ProfileNavRoute, navOptions)


fun NavGraphBuilder.profileScreen(

) {
    composable<ProfileNavRoute> {
        ProfileRoute()
    }
}
