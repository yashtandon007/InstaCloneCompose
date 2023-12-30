package com.example.instaclonecompose.feature_auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.feature_auth.presentation.nav.BottomNav

@Composable
fun FeedScreen(
    navController: NavController
) {
    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "Feed Screen", fontSize = 50.sp)
            }

        }
        BottomNav(navController = navController)
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    FeedScreen(navController = navController)
}