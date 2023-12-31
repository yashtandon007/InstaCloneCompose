package com.example.instaclonecompose.feature_auth.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.R
import com.example.instaclonecompose.feature_auth.presentation.auth.AuthViewModel
import com.example.instaclonecompose.util.Screens
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel? = null
) {
    val isAuthenticated = authViewModel?.isAuthenticated?:false
    val scale = remember {
        Animatable(0.5f)
    }
    LaunchedEffect(key1 = true) {

        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
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

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.insta),
            contentDescription = "splash screen logo",
            modifier = Modifier.scale(scale = scale.value)
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}