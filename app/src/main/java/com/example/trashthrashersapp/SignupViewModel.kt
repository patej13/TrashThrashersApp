package com.example.trashthrashersapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignupViewModel: ViewModel()  {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }
    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }
    fun signUp(onResult: (Boolean) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
    fun resetEmailPassword(){
        email = ""
        password = ""
    }
}