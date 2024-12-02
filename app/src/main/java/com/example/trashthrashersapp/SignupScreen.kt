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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun SignupScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    signupViewModel: SignupViewModel = viewModel()
) {
    val email = signupViewModel.email
    val password = signupViewModel.password
    var invalidMessage by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(10.dp)
    ) {
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
                signupViewModel.signUp { success ->
                    if (success) {
                        navController.navigate(NavigationItems.Login.route)
                    }
                    else{
                        signupViewModel.resetEmailPassword()
                        invalidMessage = "Enter a valid email or password"
                    }
                }
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}