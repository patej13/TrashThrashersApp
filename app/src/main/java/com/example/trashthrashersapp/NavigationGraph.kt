package com.example.trashthrashersapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
    }
}