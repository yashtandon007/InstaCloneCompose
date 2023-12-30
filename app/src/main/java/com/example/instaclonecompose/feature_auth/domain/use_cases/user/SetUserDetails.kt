package com.example.instaclonecompose.feature_auth.domain.use_cases.user

import com.example.instaclonecompose.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userId: String, name: String, bio: String, url: String, userName: String
    ) = repository.setUserDetails(
        userId = userId,
        name = name,
        bio = bio,
        url = url,
        userName = userName)
}