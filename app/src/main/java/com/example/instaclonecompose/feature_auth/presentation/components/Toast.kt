package com.example.instaclonecompose.feature_auth.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(message:String) {
    Toast.makeText(LocalContext.current,message,Toast.LENGTH_LONG).show()
}