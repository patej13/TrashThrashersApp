package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
    var email by  remember { mutableStateOf("") }
    var password by  remember { mutableStateOf("") }
    var trashCollected by remember { mutableStateOf("") }
    var trashMarked by remember { mutableStateOf("") }

    LaunchedEffect(user?.uid) {
        val firestoreDB = FirebaseFirestore.getInstance()
        val userInformation = user?.let { firestoreDB.collection("Info").document(it.uid) }
        userInformation?.get()?.addOnSuccessListener { document ->
            userName = document.getString("username").toString()
            email = document.getString("email").toString()
            password =  document.getString("password").toString()
            trashCollected = document.getString("trashCollected").toString()
            trashMarked = document.getString("trashMarked").toString()
        }
    }
    Column(
        modifier = modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            "Random"
        )
        Button(
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Trash Marked")
        }
        Button(
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Trash Collected")
        }
    }
}