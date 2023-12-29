package com.example.instaclonecompose.feature_auth.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.R
import com.example.instaclonecompose.feature_auth.presentation.auth.AuthViewModel
import com.example.instaclonecompose.feature_auth.presentation.nav.BottomNav
import com.example.instaclonecompose.util.Screens
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel?=null
) {

    LaunchedEffect(key1 = true) {
        val isAuthenticated = authViewModel?.isAuthenticated?:false
        delay(3.seconds)
        if (isAuthenticated) {
            navController.navigate(Screens.FeedScreen.route) {
                popUpTo(Screens.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screens.LoginScreen.route) {
                popUpTo(Screens.SplashScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxWidth()){
                Text(text = "Profile Screen", fontSize = 50.sp)
            }
            Button(onClick = {
                authViewModel?.signOut()
            }) {
                Text(text = "Logout")
            }

        }
        BottomNav(navController = navController)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProfile() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}