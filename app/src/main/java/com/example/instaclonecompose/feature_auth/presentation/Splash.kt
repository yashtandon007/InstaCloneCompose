package com.example.instaclonecompose.feature_auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showSystemUi = true, showBackground = true
)
@Composable
fun Splash(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    )

}