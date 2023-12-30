package com.example.instaclonecompose.feature_auth.domain.use_cases.auth

import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(
        userName: String,
        email: String,
        password: String
    ) = repository.signUp(
        userName,
        email,
        password
    )
}