package com.example.instaclonecompose.feature_auth.presentation.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.feature_auth.domain.use_cases.user.UserUseCases
import com.example.instaclonecompose.util.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases, firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val userId = firebaseAuth.currentUser?.uid
    val getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val setUserData = mutableStateOf<Response<Boolean>>(Response.Success(true))

    fun setUserData(
        name: String, bio: String, url: String, userName: String
    ) {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(
                    userId = userId, name = name, bio = bio, url = url, userName = userName
                ).collect{
                    setUserData.value = it
                }
            }
        }

    }

    fun getUserData() {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(
                    userId = userId).collect{
                    Log.e("yt007", "collect  getUserDetails:] $it", )
                    getUserData.value = it
                }
            }
        }

    }

}