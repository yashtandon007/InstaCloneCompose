package com.example.instaclonecompose.feature_auth.domain.repository

import com.example.instaclonecompose.feature_auth.domain.model.Post
import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.util.Response
import kotlinx.coroutines.flow.Flow


interface PostRepository {

    fun getPosts(userId:String) : Flow<Response<List<Post>>>
    fun uploadPost(
        userId:String,
        postImage:String,
        postDescription:String,
        time:String
    ): Flow<Response<Boolean>>

}