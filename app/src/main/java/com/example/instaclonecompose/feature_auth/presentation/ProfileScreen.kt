package com.example.instaclonecompose.feature_auth.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.feature_auth.presentation.nav.BottomNav
import com.example.instaclonecompose.feature_auth.presentation.user.UserViewModel
import com.example.instaclonecompose.util.Response
import com.example.instaclonecompose.util.Screens
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProfileScreen(
    navController: NavController, userViewModel: UserViewModel? = null
) {

    when (val userData = userViewModel?.getUserData?.value) {
        is Response.Error -> {

            Log.e("yt007", "ProfileScreen:error ")
        }

        is Response.Loading -> {
            Log.e("yt007", "ProfileScreen:Loading ")

        }

        is Response.Success -> {
            Log.e("yt007", "ProfileScreen:Success ")
        }

        else -> {}
    }

    LaunchedEffect(key1 = true) {
        userViewModel?.getUserData()
        userViewModel?.setUserData(
            name = "yashtandon",
            bio = "Software Developer 3",
            url = "httptestcom",
            userName = "yt007"
        )
    }

    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Profile Screen", fontSize = 50.sp)
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