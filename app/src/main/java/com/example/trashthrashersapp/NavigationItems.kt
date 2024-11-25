package com.example.trashthrashersapp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItems(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Welcome : NavigationItems("welcomeScreen")
    object Home: NavigationItems("homeScreen")

    object ProfileScreen : NavigationItems(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle
    )
}