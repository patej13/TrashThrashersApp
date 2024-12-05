package com.example.trashthrashersapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trashthrashersapp.ui.theme.CustomOrange
import com.example.trashthrashersapp.ui.theme.NeutralBackground
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AboutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val user = Firebase.auth.currentUser
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestoreDB = FirebaseFirestore.getInstance()
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var trashCollected by remember { mutableStateOf("") }
    var trashMarked by remember { mutableStateOf("") }

    LaunchedEffect(user?.uid) {
        val firestoreDB = FirebaseFirestore.getInstance()
        val userInformation = user?.let { firestoreDB.collection("Info").document(it.uid) }
        userInformation?.get()?.addOnSuccessListener { document ->
            userName = document.getString("username").toString()
            email = document.getString("email").toString()
            password = document.getString("password").toString()
            trashCollected = document.getString("trashCollected").toString()
            trashMarked = document.getString("trashMarked").toString()
        }
    }

    TwoColorProfileBackground()
        Column(
            modifier = modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .background(
                        color = NeutralBackground,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            ) {

                Text(
                    fontSize = 24.sp,
                    text = "About Our App",
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Text(
                    fontSize = 16.sp,
                    text = "The purpose of this app is to encourage trash collection on the FSC campus. This goal will be achieved by marking locations that contain litter or other trash on a map. Users will also take a picture of the trash, this picture will be uploaded. The amount of locations marked or collected will be visible in the profile tab, as well as the leaderboard tab."
                )
                Spacer(modifier = Modifier.height(60.dp))
                /*Button(
                    onClick = {
                        var trashMarkedInt = trashMarked.toInt()
                        trashMarkedInt++
                        trashMarked = trashMarkedInt.toString()
                        val user = firebaseAuth.currentUser
                        user?.let {
                            val userInfo = firestoreDB.collection("Info").document(it.uid)
                            val userData = hashMapOf(
                                "email" to email,
                                "password" to password,
                                "username" to userName,
                                "trashMarked" to trashMarked,
                                "trashCollected" to trashCollected,
                                "uid" to it.uid
                            )
                            userInfo.set(userData)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomOrange,
                        contentColor = Color.White),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.height(60.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text= "Add Trash Marked",
                            fontSize = 20.sp)
                }*/
                Spacer(modifier=Modifier.height(15.dp))

/*                Button(
                    onClick = {
                        var trashCollectedInt = trashCollected.toInt()
                        trashCollectedInt++
                        trashCollected = trashCollectedInt.toString()
                        val user = firebaseAuth.currentUser
                        user?.let {
                            val userInfo = firestoreDB.collection("Info").document(it.uid)
                            val userData = hashMapOf(
                                "email" to email,
                                "password" to password,
                                "username" to userName,
                                "trashMarked" to trashMarked,
                                "trashCollected" to trashCollected,
                                "uid" to it.uid
                            )
                            userInfo.set(userData)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomOrange,
                        contentColor = Color.White),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.height(60.dp)
                        .fillMaxWidth(),                ) {
                    Text(text ="Add Trash Collected",
                        fontSize = 20.sp)
                }*/
            }
        }
    }
