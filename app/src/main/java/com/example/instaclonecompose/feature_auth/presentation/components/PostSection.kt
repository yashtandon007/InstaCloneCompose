package com.example.instaclonecompose.feature_auth.presentation.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.instaclonecompose.feature_auth.presentation.post.PostViewModel
import com.example.instaclonecompose.util.Response

@Composable
fun PostSection(
    modifier: Modifier = Modifier, postViewModel: PostViewModel? = null
) {
    LaunchedEffect(key1 = true) {
        Log.e("yt007", "Success>>>>>>>>!2122  loading")
        postViewModel?.getPosts()
    }

    when (val response = postViewModel?.posts?.value) {
        is Response.Error -> {
            Log.e("yt007", "response.message" + response.message)
            // Toast(response.message)
        }

        Response.Loading -> {
            Log.e("yt007", "Loading")
//            CircularProgressIndicator(
//                modifier = Modifier.size(30.dp)
//            )
        }

        is Response.Success -> {
            Log.e("yt007", "Success>>>>>>>>!2122")

            LazyVerticalGrid(columns = GridCells.Fixed(3),
                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
                ), content = {
                    items(response.data.size) {
                        Image(
                            painter = rememberImagePainter(response.data[it].postImage),
                            contentDescription = "Banner",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .padding(6.dp)
                                .border(width = 1.dp, color = Color.White)
                        )
                    }
                })


        }

        else -> {}
    }


}