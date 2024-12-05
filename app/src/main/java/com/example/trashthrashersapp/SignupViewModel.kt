package com.example.trashthrashersapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel()  {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var userName by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }
    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }
    fun onPasswordConfirmChange(newpasswordConfirm: String) {
        passwordConfirm = newpasswordConfirm
    }
    fun onUsernameChange(newUsername: String) {
        userName = newUsername
    }
    fun resetEmailPassword(){
        email = ""
        password = ""
        passwordConfirm = ""
        userName = ""
    }
}