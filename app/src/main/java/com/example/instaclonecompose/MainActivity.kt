package com.example.instaclonecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.feature_auth.presentation.FeedScreen
import com.example.instaclonecompose.feature_auth.presentation.LoginScreen
import com.example.instaclonecompose.feature_auth.presentation.SignUpScreen
import com.example.instaclonecompose.feature_auth.presentation.SplashScreen
import com.example.instaclonecompose.feature_auth.presentation.auth.AuthViewModel
import com.example.instaclonecompose.ui.theme.InstaCloneComposeTheme
import com.example.instaclonecompose.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberNavController()
            val authViewModel: AuthViewModel = hiltViewModel()
            InstaCloneApp(navController = navHostController, authViewModel)
        }
    }
}


@Composable
fun InstaCloneApp(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController, authViewModel)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController,authViewModel)
        }
        composable(Screens.SignupScreen.route) {
            SignUpScreen(navController,authViewModel)
        }
        composable(Screens.FeedScreen.route) {
            FeedScreen(navController)
        }
    }
}