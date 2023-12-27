package com.example.instaclonecompose.feature_auth.domain.use_cases

import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignOut @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.signOut()
}