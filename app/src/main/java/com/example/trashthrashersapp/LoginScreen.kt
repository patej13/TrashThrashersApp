package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    val email = loginViewModel.email
    val password = loginViewModel.password
    var invalidMessage by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        EmailField(
            labelText = "Email",
            textInput = email,
            onValueChange = {loginViewModel.onEmailChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        PasswordField(
            labelText = "Password",
            textInput = password,
            onValueChange = {loginViewModel.onPasswordChange(it)},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                loginViewModel.loginUser { success ->
                    if (success) {
                        navController.navigate(NavigationItems.ProfileScreen.route)
                    } else {
                        //navController.navigate(NavigationItems.Login.route)
                        loginViewModel.resetEmailPassword()
                        invalidMessage = "Improper Credentials"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Log In")
        }
        Text(
            text = invalidMessage
        )
    }
}
@Composable
fun EmailField(
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
fun PasswordField(
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