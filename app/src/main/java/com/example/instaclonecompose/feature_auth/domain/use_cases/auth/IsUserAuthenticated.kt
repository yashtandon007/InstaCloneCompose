package com.example.instaclonecompose.feature_auth.domain.use_cases.auth

import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isAuthenticated()
}