package com.example.trashthrashersapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.trashthrashersapp.ui.theme.CustomOrange
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
    val passwordConfirm = signupViewModel.passwordConfirm
    val trashMarked = "0"
    val trashCollected = "0"
    var invalidMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var properValidation by remember { mutableStateOf("") }
    var properEmailValidation by remember { mutableStateOf("") }
    var properPasswordValidation by remember { mutableStateOf("") }
    var properPasswordConfirm by remember { mutableStateOf("") }

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestoreDB = FirebaseFirestore.getInstance()

    fun signUp() {
        if (email.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty() && password == passwordConfirm) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
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
                        navController.navigate(NavigationItems.Login.route)
                    }
                }
                else{
                    signupViewModel.resetEmailPassword()
                    if(email.isEmpty() || password.isEmpty() || userName.isEmpty() || passwordConfirm.isEmpty()){
                        invalidMessage = "Fields Cannot be Empty"
                    }
                    else{
                        errorMessage = "Error in Signup Process:"
                        invalidMessage = "Enter a valid email or password"
                        properValidation = "Emails and passwords must follow the following format:"
                        properEmailValidation = "4 letters + @ + valid domain (ex .com)"
                        properPasswordValidation = "Password must be at least 5 characters"
                        properPasswordConfirm = "Make sure password and confirm password match"
                    }
                }
            }
        }
    }
    TwoColorSignUpBackground()

    Box(
        modifier = modifier
            .padding(top = 100.dp, start = 16.dp, end = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
            .padding(5.dp)

    ) {


        Column(
            modifier = modifier.padding(10.dp)
        ) {
            Box(
                modifier = modifier
                    .height(80.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.appname),
                    contentDescription = "Splash Screen logo",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop
                )
            }

            UsernameField(
                labelText = "Username",
                textInput = userName,
                onValueChange = { signupViewModel.onUsernameChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
            SignUpEmailField(
                labelText = "Email",
                textInput = email,
                onValueChange = { signupViewModel.onEmailChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
            SignUpPasswordField(
                labelText = "Password",
                textInput = password,
                onValueChange = { signupViewModel.onPasswordChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
            SignUpPasswordConfirmField(
                labelText = "Password Confirmation",
                textInput = passwordConfirm,
                onValueChange = { signupViewModel.onPasswordConfirmChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier=Modifier.height(15.dp))
            Button(
                onClick = {
                    signUp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomOrange,
                    contentColor = Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.height(60.dp)
                    .fillMaxWidth(),

            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 20.sp

                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Emails and passwords must follow the following format:"
            )
            Text(
                text = "4 letters + @ + valid domain (ex .com)"
            )
            Text(
                text = "Password must be at least 6 characters"
            )
            Text(
                text = errorMessage
            )
            Text(
                text = invalidMessage
            )
/*            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Blue)) {
                        append("Login!")
                    }
                },

                fontSize = 20.sp
            )*/

            /*SocialMediaRow()*/
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@Composable
fun SignUpPasswordConfirmField(
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
