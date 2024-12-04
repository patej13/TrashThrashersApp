package com.example.trashthrashersapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.trashthrashersapp.ui.theme.CustomMint
import com.example.trashthrashersapp.ui.theme.CustomOrange
import com.example.trashthrashersapp.ui.theme.CustomRed
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
WhiteBackground {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Centers the content horizontally
        verticalArrangement = Arrangement.Top
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.beach_clean),
                contentDescription = "Clean up",
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp)
            )
        }
              Text(
        text = "Welcome!",
        fontSize = 24.sp,
        letterSpacing = 1.sp,
        lineHeight = 40.sp,
        modifier = Modifier
    )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), // Add padding as needed
            contentAlignment = Alignment.Center // Center horizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally // Center elements horizontally
            ) {

                Text(
                    text = "Now you can help keep your",
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 40.sp
                )
                Text(
                    text = "community clean too!",
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 40.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
                navController.navigate(NavigationItems.ProfileScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTeal,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp) // Custom width and height

        ) {
            Text(fontSize = 18.sp, text = "Go to App")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                navController.navigate(NavigationItems.Login.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomOrange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
        ) {
            Text(fontSize = 18.sp, text = "Log in")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                navController.navigate(NavigationItems.Signup.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomRed,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
        ) {
            Text(fontSize = 18.sp, text = "Sign Up")
        }
    }
}

}