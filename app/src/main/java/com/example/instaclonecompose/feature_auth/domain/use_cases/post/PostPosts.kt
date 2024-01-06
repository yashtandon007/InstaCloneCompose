package com.example.instaclonecompose.feature_auth.domain.use_cases.post

import com.example.instaclonecompose.feature_auth.domain.repository.PostRepository
import com.example.instaclonecompose.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class PostPosts @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(
        userId:String,
        postId:String,
        postImage:String,
        postDescription:String,
        time:String
    ) = repository.uploadPost(
        userId,
        postId,
        postImage,
        postDescription,
        time
    )
}