package com.example.instaclonecompose.feature_auth.presentation.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonecompose.util.Screens


enum class BottomNavItem(
    val icon: ImageVector, val route: String
) {

    FEED(Icons.Default.Home, Screens.FeedScreen.route), SEARCH(
        Icons.Default.Search, Screens.SearchScreen.route
    ),
    PROFILE(Icons.Default.Person, Screens.ProfileScreen.route)
}

@Composable
fun BottomNav(
    selectedItem: BottomNavItem = BottomNavItem.FEED, navController: NavController
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        for (item in BottomNavItem.values()) {
            Image(
                imageVector = item.icon,
                contentDescription = "item",
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(item.route)
                    },
                colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)
                else ColorFilter.tint(Color.Gray)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    BottomNav(navController = navController)
}