package com.example.instaclonecompose.feature_auth.domain.repository

import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.util.Response
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    fun getUserDetails(userId:String) : Flow<Response<User>>
    fun setUserDetails(
        userId:String,
        name:String,
        bio:String,
        url:String,
        userName:String
    ): Flow<Response<Boolean>>

}