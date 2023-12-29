package com.example.instaclonecompose.util

sealed class Screens
    (val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignupScreen : Screens("signup_screen")
    object FeedScreen : Screens("feed_screen")
    object SearchScreen : Screens("search_screen")
    object ProfileScreen : Screens("profile_screen")
}