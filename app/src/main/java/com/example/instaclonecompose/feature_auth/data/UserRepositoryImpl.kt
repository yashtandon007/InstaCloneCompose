package com.example.instaclonecompose.feature_auth.data

import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.feature_auth.domain.repository.UserRepository
import com.example.instaclonecompose.util.Constants
import com.example.instaclonecompose.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override fun getUserDetails(userId: String): Flow<Response<User>> = callbackFlow {
        trySend(Response.Loading)
        val listener = firestore.collection(
            Constants.COLLECTION_NAME_USERS
        ).document(userId).addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                val obj = snapshot.toObject(User::class.java)!!
                trySend(Response.Success(obj))
            } else {
                trySend(Response.Error(error?.message ?: error.toString()))
            }
        }
        awaitClose {
            listener.remove()
        }
    }

    override fun setUserDetails(
        userId: String, name: String, bio: String, url: String, userName: String
    ): Flow<Response<Boolean>> = callbackFlow {
        trySend(Response.Loading)
        val map = mutableMapOf<String, String>()
        map["name"] = name
        map["bio"] = bio
        map["name"] = name
        map["userName"] = userName
        firestore.collection(Constants.COLLECTION_NAME_USERS).document(userId)
            .update(map as Map<String, Any>)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(Response.Success(true))
                } else {
                    trySend(Response.Error("Failed to set user, ${it.exception?.localizedMessage} "))
                }
            }
        awaitClose {

        }
    }


}