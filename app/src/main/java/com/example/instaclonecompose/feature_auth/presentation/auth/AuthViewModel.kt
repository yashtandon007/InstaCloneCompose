package com.example.instaclonecompose.feature_auth.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonecompose.feature_auth.domain.use_cases.AuthUseCases
import com.example.instaclonecompose.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCases, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isAuthenticated get() = useCases.isUserAuthenticated()
    val signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val authState = mutableStateOf(false)

    fun signOut() {
        viewModelScope.launch {
            useCases.signOut().collect {
                signOutState.value = it
            }
        }
    }

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            useCases.signIn(
                email,
                password
            ).collect {
                signInState.value = it
            }
        }
    }

    fun signUp(
        userName: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            useCases.signUp(
                userName,
                email,
                password
            ).collect {
                signUpState.value = it
            }
        }
    }

    fun auth() {
        viewModelScope.launch {
            useCases.authState().collect {
                authState.value = it
            }
        }
    }


}
