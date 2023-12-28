package com.example.instaclonecompose.feature_auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()){

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSignOut() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}