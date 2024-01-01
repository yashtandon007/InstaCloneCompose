package com.example.instaclonecompose.feature_auth.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instaclonecompose.R

@Composable
fun PostSection(
    modifier: Modifier = Modifier, posts: List<Painter> = listOf(
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta),
        painterResource(id = R.drawable.insta)
    )
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        // content padding
        contentPadding = PaddingValues(
            start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
        ), content = {
            items(posts.size) {
                Image(
                    painter = posts[it],
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