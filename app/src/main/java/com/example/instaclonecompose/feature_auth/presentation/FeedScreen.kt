package com.example.instaclonecompose.feature_auth.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.instaclonecompose.feature_auth.domain.model.Post
import com.example.instaclonecompose.feature_auth.presentation.components.Toast
import com.example.instaclonecompose.feature_auth.presentation.nav.BottomNav
import com.example.instaclonecompose.feature_auth.presentation.post.PostViewModel
import com.example.instaclonecompose.util.Response

@Composable
fun FeedScreen(
    navController: NavController, postViewModel: PostViewModel? = null
) {

    LaunchedEffect(key1 = true) {

        postViewModel?.getPosts()
    }

    when (val response = postViewModel?.posts?.value) {
        is Response.Error -> {
            Log.e("yt007", "response.message" + response.message)
            Toast(response.message)
        }

        Response.Loading -> {
            Log.e("yt007", "Loading")
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp)
            )
        }

        is Response.Success -> {
            FeedHome(response.data)
        }

        else -> {}
    }
    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {

        }
        BottomNav(navController = navController)
    }

}

@Composable
private fun FeedHome(data: List<Post>) {
    Log.e("yt007", "FeedHome: ${data.size}" )
    LazyColumn {
        items(data) {
            Column {
                Log.e("yt007", "url: ${it.postImage}" )
                Image(
                    painter = rememberImagePainter(it.postImage), contentDescription = null,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp)
                    ,
                    contentScale = ContentScale.FillWidth
                    )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    FeedScreen(navController = navController)
}