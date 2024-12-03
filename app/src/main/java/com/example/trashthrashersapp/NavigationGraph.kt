package com.example.trashthrashersapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * Navigation Graph, add a composable to the function below for any classes you add
 * Make sure there is an item in navigation items too
 * Model after the first two for anything that might require a new screen that is not related to the navigation bar contents
 * Model after the profile screen for navigation bar items
 */
@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    NavHost(navController, startDestination = NavigationItems.Welcome.route) {
        composable(NavigationItems.Welcome.route) {
            onBottomBarVisibilityChanged(false)
            SplashScreen(navController = navController)
        }
        composable(NavigationItems.Home.route) {
            onBottomBarVisibilityChanged(false)
            HomeScreen(navController = navController)
        }
        composable(NavigationItems.ProfileScreen.route) {
            onBottomBarVisibilityChanged(true)
            ProfileScreen()
        }
        composable(NavigationItems.Login.route) {
            onBottomBarVisibilityChanged(false)
            LoginScreen(navController = navController)
        }
        composable(NavigationItems.Signup.route) {
            onBottomBarVisibilityChanged(false)
            SignupScreen(navController = navController)
        }
        composable(NavigationItems.MapScreen.route) {
            onBottomBarVisibilityChanged(true)
            MapScreen()
        }
    }
}