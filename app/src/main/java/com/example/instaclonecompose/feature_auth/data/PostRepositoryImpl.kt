package com.example.instaclonecompose.feature_auth.data

import android.util.Log
import com.example.instaclonecompose.feature_auth.domain.model.Post
import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.feature_auth.domain.repository.PostRepository
import com.example.instaclonecompose.feature_auth.domain.repository.UserRepository
import com.example.instaclonecompose.util.Constants
import com.example.instaclonecompose.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : PostRepository {
    override fun getPosts(userId: String): Flow<Response<List<Post>>> = callbackFlow {
        trySend(Response.Loading)
        firestore.collection(Constants.COLLECTION_NAME_POSTS)
            //.whereNotEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (snapshot != null) {
                    Log.e("yt007", "repo  getUserDetails: success")
                    val obj = snapshot.toObjects(Post::class.java)
                    trySend(Response.Success(obj))
                } else {
                    Log.e("yt007", "repo getUserDetails: faled")
                    trySend(Response.Error(error?.message ?: error.toString()))
                }
            }
        awaitClose {}
    }

    override fun uploadPost(
        userId: String, postImage: String, postDescription: String, time: String
    ): Flow<Response<Boolean>> = callbackFlow {
        Log.e("yt007", "uploadPost:>>> 111", )
        trySend(Response.Loading)
        val postId = firestore.collection(Constants.COLLECTION_NAME_POSTS).document().id
        val post = Post(
            useId = userId,
            postId = postId,
            postImage = postImage,
            postDescription = postDescription,
            time = time
        )
        firestore.collection(Constants.COLLECTION_NAME_POSTS).document(postId).set(post)
            .addOnCompleteListener {
                Log.e("yt007", "uploadPost:>>> $it", )
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