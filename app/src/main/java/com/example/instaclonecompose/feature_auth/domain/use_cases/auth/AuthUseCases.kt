package com.example.instaclonecompose.feature_auth.domain.use_cases.auth

data class AuthUseCases(

    val isUserAuthenticated: IsUserAuthenticated,
    val authState: AuthState,
    val signIn: SignIn,
    val signOut: SignOut,
    val signUp: SignUp
)
