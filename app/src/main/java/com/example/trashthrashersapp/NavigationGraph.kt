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
        composable(NavigationItems.CameraScreen.route) {
            onBottomBarVisibilityChanged(true)
            CameraScreen()
        }
        composable(NavigationItems.ProfileScreen.route) {
            onBottomBarVisibilityChanged(true)
            ProfileScreen(navController = navController)
        }
        composable(NavigationItems.AboutScreen.route) {
            onBottomBarVisibilityChanged(true)
            AboutScreen(navController = navController)
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