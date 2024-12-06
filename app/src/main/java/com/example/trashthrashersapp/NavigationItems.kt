package com.example.trashthrashersapp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItems(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Welcome : NavigationItems("welcomeScreen")
    object Home: NavigationItems("homeScreen")
    object Login: NavigationItems("loginScreen")
    object Signup: NavigationItems("signupScreen")
    object Camera: NavigationItems("cameraScreen")

    object ProfileScreen : NavigationItems(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle
    )
    object CameraScreen : NavigationItems(
        route = "camera",
        title = "Camera",
        icon = Icons.Outlined.AddCircle
    )
    object MapScreen : NavigationItems(
        route = "map",
        title = "Map",
        icon = Icons.Outlined.AccountCircle
    )


    object AboutScreen : NavigationItems(
        route = "about",
        title = "About",
        icon = Icons.Outlined.Info
    )

}
