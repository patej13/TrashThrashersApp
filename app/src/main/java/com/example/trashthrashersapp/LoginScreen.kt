package com.example.trashthrashersapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trashthrashersapp.ui.theme.CustomOrange
import com.example.trashthrashersapp.ui.theme.CustomRed
import com.example.trashthrashersapp.ui.theme.CustomTeal
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    val email = loginViewModel.email
    val password = loginViewModel.password
    var invalidMessage by remember { mutableStateOf("") }

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    fun loginUser() {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(NavigationItems.ProfileScreen.route)
                } else {
                    loginViewModel.resetEmailPassword()
                    if (email.isEmpty()) {
                        invalidMessage = "Email Field Cannot be Empty"
                    }
                    if (password.isEmpty()) {
                        invalidMessage = "Password Field Cannot be Empty"
                    } else {
                        invalidMessage = "Email or Password is incorrect"
                    }
                }
            }
        }
    }



    TwoColorBackgroundColumn()
    Column(

        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = modifier
                .padding(16.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    2.dp,
                    Color.Gray,
                    RoundedCornerShape(10.dp)
                )
                .padding(5.dp)
                .height(600.dp)
        ) {
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .height(80.dp)
                        .padding(top = 20.dp)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.appname),
                        contentDescription = "Splash Screen logo",
                        modifier = Modifier,
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = "We've missed you, welcome back! ",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)

                )
                Text(
                    text = "Login Here ",
                    fontSize = 26.sp,
                    color = CustomRed,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                EmailField(
                    labelText = "Email",
                    textInput = email,
                    onValueChange = { loginViewModel.onEmailChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordField(
                    labelText = "Password",
                    textInput = password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        loginUser()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomOrange,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Log In",
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = invalidMessage
                )

                Text(
                    text = "Forgot your password? ",
                    fontSize = 16.sp,
                    color = CustomRed,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        .clickable {  }
                )




                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account? ")
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Blue)) {
                            append("Sign up!")
                        }
                    },
                    fontSize = 16.sp,
                )
                SocialMediaRow()
            }
        }
    }
}

@Composable
fun SocialMediaRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFFFFFFF), shape = CircleShape)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.googleicon),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp)
            )
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFFFFFFF), shape = CircleShape)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.facebookicon),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp)
            )
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFFFFFFF), shape = CircleShape)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.twittericon),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp)
            )
        }

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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()

        )
    }
}