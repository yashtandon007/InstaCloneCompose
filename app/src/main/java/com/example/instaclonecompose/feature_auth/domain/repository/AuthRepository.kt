package com.example.instaclonecompose.feature_auth.domain.repository

import com.example.instaclonecompose.util.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isAuthenticated(): Boolean

    fun getAuthenticatedState(): Flow<Boolean>

    fun signIn(
        email:String,
        password:String
    ):Flow<Response<Boolean>>

    fun signOut():Flow<Response<Boolean>>

    fun signUp(
        userName:String,
        email: String,
        password: String
    ): Flow<Response<Boolean>>


}