package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

/**
 * This is where the profile info goes, just contains text at the moment
 */
@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val user = Firebase.auth.currentUser
    var userName by remember { mutableStateOf("") }

    LaunchedEffect(user?.uid) {
        val firestoreDB = FirebaseFirestore.getInstance()
        val userInformation = user?.let { firestoreDB.collection("Info").document(it.uid) }
        userInformation?.get()?.addOnSuccessListener { document ->
            userName = document.getString("username").toString()
        }
    }
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            "Temporary Profile Screen"
        )
        if (user != null) {
            Text("Hello $userName")
        } else {
            Text("")
        }
        Button(
            onClick = {
                Firebase.auth.signOut()
                navController.navigate(NavigationItems.Home.route)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Log Out")
        }
    }
}