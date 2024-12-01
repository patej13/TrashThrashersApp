package com.example.trashthrashersapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun SignupScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    signupViewModel: SignUpViewModel = viewModel()
) {
    val email = signupViewModel.email
    val password = signupViewModel.password
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
                signupViewModel.signUpUser { isSuccess ->
                    if (isSuccess) {
                        navController.navigate(NavigationItems.Login.route)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Sign Up")
        }
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