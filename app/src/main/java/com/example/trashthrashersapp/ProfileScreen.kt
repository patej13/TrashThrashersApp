package com.example.trashthrashersapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.trashthrashersapp.ui.theme.CustomOrange
import com.example.trashthrashersapp.ui.theme.CustomRed
import com.example.trashthrashersapp.ui.theme.NeutralBackground



@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val user = Firebase.auth.currentUser
    var userName by remember { mutableStateOf("") }
    var trashCollected by remember { mutableStateOf("") }
    var trashMarked by remember { mutableStateOf("") }

    LaunchedEffect(user?.uid) {
        val firestoreDB = FirebaseFirestore.getInstance()
        val userInformation = user?.let { firestoreDB.collection("Info").document(it.uid) }
        userInformation?.get()?.addOnSuccessListener { document ->
            userName = document.getString("username").toString()
            trashCollected = document.getString("trashCollected").toString()
            trashMarked = document.getString("trashMarked").toString()
        }
    }

    TwoColorProfileBackground()
/*TealBackground {*/
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Profile",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 36.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_profile), // Replace with your drawable
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter)
                    .zIndex(2f),
                contentScale = ContentScale.Crop
            )

            // User Info Box
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .background(
                        color = NeutralBackground,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                // Username
                Text(
                    text = userName,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                if (user != null) {
                    Text("Hello $userName")
                } else {
                    Text("")
                }
                Text(
                    "Trash Marked: $trashMarked"
                )
                Text(
                    "Trash Collected: $trashCollected"
                )
                Button(
                    onClick = {
                        Firebase.auth.signOut()
                        navController.navigate(NavigationItems.Home.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomOrange,
                        contentColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                   Text(fontSize = 20.sp, text ="Log Out")

                }
                Button(
                    onClick = {
                        if (user != null) {
                            user.delete()
                        }
                        navController.navigate(NavigationItems.Home.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomRed,
                        contentColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(fontSize = 20.sp, text ="Delete Account")
                }
            }
        }
    }
}

