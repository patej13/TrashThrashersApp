package com.example.trashthrashersapp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation Items, add an object to the function below
 * Model after the first two for anything that might require a new screen that is not related to the navigation bar contents
 * Model after the profile screen for navigation bar items
 */
sealed class NavigationItems(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Welcome : NavigationItems("welcomeScreen")
    object Home: NavigationItems("homeScreen")
    object Login: NavigationItems("loginScreen")
    object Signup: NavigationItems("signupScreen")

    object ProfileScreen : NavigationItems(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle
    )
}