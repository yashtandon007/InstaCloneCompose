package com.example.instaclonecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.feature_auth.presentation.FeedScreen
import com.example.instaclonecompose.feature_auth.presentation.LoginScreen
import com.example.instaclonecompose.feature_auth.presentation.ProfileScreen
import com.example.instaclonecompose.feature_auth.presentation.SearchScreen
import com.example.instaclonecompose.feature_auth.presentation.SignUpScreen
import com.example.instaclonecompose.feature_auth.presentation.SplashScreen
import com.example.instaclonecompose.feature_auth.presentation.auth.AuthViewModel
import com.example.instaclonecompose.feature_auth.presentation.user.UserViewModel
import com.example.instaclonecompose.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberNavController()
            val authViewModel: AuthViewModel = hiltViewModel()
           val userViewModel: UserViewModel = hiltViewModel()
            InstaCloneApp(navController = navHostController, authViewModel, userViewModel = userViewModel)
        }
    }
}


@Composable
fun InstaCloneApp(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
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
        composable(Screens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(navController,userViewModel)
        }
    }
}