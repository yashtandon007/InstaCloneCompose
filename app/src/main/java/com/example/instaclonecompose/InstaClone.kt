package com.example.instaclonecompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
object InstaClone : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}