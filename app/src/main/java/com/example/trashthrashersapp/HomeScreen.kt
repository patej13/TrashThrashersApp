package com.example.trashthrashersapp

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trashthrashersapp.ui.theme.CustomTeal
import com.example.trashthrashersapp.ui.theme.NeutralBackground

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
    TwoColorBackgroundColumn()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Centers the content horizontally
        verticalArrangement = Arrangement.Top)
        {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.homeimage),
                contentDescription = "Clean up",
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp)
            )
        }
        Text(text = "With your help we will be able to help clean our neighborhoods!",
            fontSize = 36.sp,
            letterSpacing = 1.sp,
            lineHeight = 40.sp,
            modifier = Modifier.padding(vertical = 60.dp))

        Button(onClick = {
            navController.navigate(NavigationItems.ProfileScreen.route)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTeal,
                contentColor = Color.White),
            modifier = Modifier.fillMaxWidth()
                .padding(top = 40.dp)) {
            Text(fontSize = 18.sp, text ="Go to App")
        }



        Button(onClick = {
            navController.navigate(NavigationItems.Login.route)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTeal,
                contentColor = Color.White),
            modifier = Modifier.fillMaxWidth()) {
            Text(fontSize = 18.sp, text ="Log in")
        }



        Button(onClick = {
            navController.navigate(NavigationItems.Signup.route)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTeal,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text(fontSize = 18.sp, text ="Sign Up")
        }
    }
}