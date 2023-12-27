package com.example.instaclonecompose.feature_auth.domain.use_cases

import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignIn @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(
        email:String,
        password:String
    ) = repository.signIn(
        email,
        password
    )
}