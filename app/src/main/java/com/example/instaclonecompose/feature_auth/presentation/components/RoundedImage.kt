package com.example.instaclonecompose.feature_auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun RoundedImage(
    modifier: Modifier, imageUrl: String
) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = "user",
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = false)
            .border(
                shape = CircleShape, color = Color.LightGray, width = 1.dp
            )
            .clip(
                CircleShape
            )
    )
}