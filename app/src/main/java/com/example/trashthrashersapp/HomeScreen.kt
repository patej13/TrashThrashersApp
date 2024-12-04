package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

/**
 * Temporary Home Screen
 * This is supposed to be the screen with 2 buttons, sign in or sign up
 * It has a path to the main contents of the app temporarily, so the the sign in can be developed
 * at the same time as the rest of the app. It also allows us to skip the sign in process during testing
 */
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).padding(10.dp)){
        Button(onClick = {
            navController.navigate(NavigationItems.ProfileScreen.route)
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(fontSize = 16.sp, text ="Go to App")
        }
        Button(onClick = {
            navController.navigate(NavigationItems.Login.route)
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(fontSize = 16.sp, text ="Log in")
        }
        Button(onClick = {
            navController.navigate(NavigationItems.Signup.route)
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(fontSize = 16.sp, text ="Sign Up")
        }
    }
}