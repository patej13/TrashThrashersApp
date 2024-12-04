package com.example.trashthrashersapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }
    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }
    fun resetEmailPassword(){
        email = ""
        password = ""
    }
}