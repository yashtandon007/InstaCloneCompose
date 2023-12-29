package com.example.instaclonecompose.feature_auth.data

import android.util.Log
import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.feature_auth.domain.repository.AuthRepository
import com.example.instaclonecompose.util.Constants
import com.example.instaclonecompose.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun isAuthenticated() = auth.currentUser != null

    override fun getAuthenticatedState(): Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(listener)
        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }

    override fun signIn(email: String, password: String): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading)
        if (email.isBlank() || password.isBlank()) {
            trySend(Response.Error("Email or Password is empty"))

        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.e("yt007","sign in  successs...........")

                    trySend(Response.Success(true))
                } else {
                    trySend(Response.Error(it.exception?.localizedMessage ?: "Failed to sign in"))
                }
            }
        }

        awaitClose {}
    }


    override fun signOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override fun signUp(
        userName: String, email: String, password: String
    ): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading)
        if (email.isBlank() || password.isBlank()  || userName.isBlank() ) {
            trySend(Response.Error("Username, Email or Password is empty"))

        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                val userID = auth.currentUser?.uid!!
                if (it.isSuccessful) {
                    val user = User(
                        userName = userName,
                        useId = userID,
                        password = password,
                        email = email
                    )
                    firestore.collection(Constants.COLLECTION_NAME_USERS)
                        .document(userID)
                        .set(user)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful) {
                                trySend(Response.Success(true))
                            } else {
                                trySend(
                                    Response.Error(
                                        result.exception?.localizedMessage
                                            ?: "Failed to create user"
                                    )
                                )
                            }
                        }
                } else {
                    trySend(
                        Response.Error(
                            it.exception?.localizedMessage ?: "Failed to create account"
                        )
                    )
                }
            }
        }
        awaitClose {}
    }


}