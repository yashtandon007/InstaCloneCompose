package com.example.instaclonecompose.feature_auth.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.R
import com.example.instaclonecompose.feature_auth.presentation.auth.AuthViewModel
import com.example.instaclonecompose.feature_auth.presentation.components.Toast
import com.example.instaclonecompose.util.Response
import com.example.instaclonecompose.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController, authViewModel: AuthViewModel? = null
) {
    val userNameState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = painterResource(id = R.drawable.insta),
                contentDescription = "logo",
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 16.dp)
            )
            Text(
                text = "Sign up",
                modifier = Modifier.padding(16.dp),
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif
            )

            OutlinedTextField(value = userNameState.value, onValueChange = {
                userNameState.value = it
            }, modifier = Modifier.padding(8.dp), label = {
                Text(text = "Username")
            })
            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value = it
            }, modifier = Modifier.padding(8.dp), label = {
                Text(text = "Email")
            })


            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value = it
            }, modifier = Modifier.padding(8.dp), label = {
                Text(text = "Password")
            })
            Button(onClick = {
                authViewModel?.signUp(
                    userNameState.value, emailState.value, passwordState.value
                )
            }, modifier = Modifier.padding(6.dp)) {
                Text(text = "Sign up")
            }

            when (val response = authViewModel?.signUpState?.value) {
                is Response.Error -> {
                    Log.e("yt007", "response.message" + response.message)
                    Toast(response.message)
                }

                is Response.Loading -> {
                    Log.e("yt007", "Loading")
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp)
                    )
                }

                is Response.Success -> {
                    if (response.data) {
                        LaunchedEffect(key1 = false) {
                            Log.e("yt007", "Success SignUp" + response.data)
                            navController.navigate(Screens.FeedScreen.route) {
                                popUpTo(Screens.SignupScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

                else -> {}
            }

            Text(
                "Already a User? Sign In",
                modifier = Modifier
                    .padding(6.dp)
                    .clickable {
                        navController.navigate(Screens.LoginScreen.route) {
                            launchSingleTop = true
                        }
                    }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}