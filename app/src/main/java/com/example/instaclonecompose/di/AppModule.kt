package com.example.instaclonecompose.di

import com.example.instaclonecompose.feature_auth.data.AuthRepositoryImpl
import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import com.example.instaclonecompose.feature_auth.domain.use_cases.AuthState
import com.example.instaclonecompose.feature_auth.domain.use_cases.AuthUseCases
import com.example.instaclonecompose.feature_auth.domain.use_cases.IsUserAuthenticated
import com.example.instaclonecompose.feature_auth.domain.use_cases.SignIn
import com.example.instaclonecompose.feature_auth.domain.use_cases.SignOut
import com.example.instaclonecompose.feature_auth.domain.use_cases.SignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesFirebaseStorage() = FirebaseStorage.getInstance()

    @Singleton
    @Provides
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun providesAuthRepository(
        auth: FirebaseAuth, firestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(
            auth, firestore
        )
    }

    @Singleton
    @Provides
    fun providesAuthUseCase(
        authRepository: AuthRepository
    ) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticated(
            authRepository
        ), authState = AuthState(
            authRepository
        ), signIn = SignIn(
            authRepository
        ), signOut = SignOut(
            authRepository
        ), signUp = SignUp(
            authRepository
        )
    )
}