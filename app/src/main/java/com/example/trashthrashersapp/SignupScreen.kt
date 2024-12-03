package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    signupViewModel: SignupViewModel = viewModel()
) {
    val email = signupViewModel.email
    val password = signupViewModel.password
    val userName = signupViewModel.userName
    var invalidMessage by remember { mutableStateOf("") }
    var properValidation by remember { mutableStateOf("") }
    var properEmailValidation by remember { mutableStateOf("") }
    var properPasswordValidation by remember { mutableStateOf("") }

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestoreDB = FirebaseFirestore.getInstance()

    fun signUp() {
        if (email.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.let {
                        val userInfo = firestoreDB.collection("Info").document(it.uid)
                        val userData = hashMapOf(
                            "email" to email,
                            "password" to password,
                            "username" to userName,
                            "uid" to it.uid
                        )
                        userInfo.set(userData)
                        navController.navigate(NavigationItems.Login.route)
                    }
                }
                else{
                    signupViewModel.resetEmailPassword()
                    if(email.isEmpty()){
                        invalidMessage = "Email Field Cannot be Empty"
                    }
                    if(password.isEmpty()){
                        invalidMessage = "Password Field Cannot be Empty"
                    }
                    if(userName.isEmpty()){
                        invalidMessage = "Username Field Cannot be Empty"
                    }
                    else{
                        invalidMessage = "Enter a valid email or password"
                        properValidation = "Emails and passwords must follow the following format:"
                        properEmailValidation = "4 letters + @ + valid domain (ex .com)"
                        properPasswordValidation = "Must be at least 4 characters or digits"
                    }
                }
            }
        }
    }
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        UsernameField(
            labelText = "Username",
            textInput = userName,
            onValueChange = {signupViewModel.onUsernameChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        SignUpEmailField(
            labelText = "Email",
            textInput = email,
            onValueChange = {signupViewModel.onEmailChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        SignUpPasswordField(
            labelText = "Password",
            textInput = password,
            onValueChange = {signupViewModel.onPasswordChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                signUp()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Sign Up")
        }
        Text(
            text = invalidMessage
        )
    }
}
@Composable
fun SignUpEmailField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TextField(
            value = textInput,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun SignUpPasswordField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TextField(
            value = textInput,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun UsernameField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TextField(
            value = textInput,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}