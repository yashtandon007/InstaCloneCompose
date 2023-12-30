package com.example.instaclonecompose.feature_auth.domain.use_cases.user

import com.example.instaclonecompose.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userId:String
    ) = repository.getUserDetails(userId)
}