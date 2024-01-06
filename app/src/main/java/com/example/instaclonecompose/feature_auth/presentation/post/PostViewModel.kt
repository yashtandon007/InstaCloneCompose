package com.example.instaclonecompose.feature_auth.presentation.post

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonecompose.feature_auth.domain.model.Post
import com.example.instaclonecompose.feature_auth.domain.use_cases.post.PostUseCases
import com.example.instaclonecompose.util.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCases: PostUseCases, firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val userId = firebaseAuth.currentUser?.uid?:""
    val posts = mutableStateOf<Response<List<Post>>>(Response.Loading)
    val uploadPost = mutableStateOf<Response<Boolean>>(Response.Success(false))

    fun getPosts() {
        Log.e("yt007", "Success>>>>>>>>!2122  getPosts")
        viewModelScope.launch {
            useCases.getPosts(userId).collect {
                posts.value = it
            }
        }
    }

    fun postPosts(
        postImage:String,
        postDescription:String,
        time:String
    ) {
        viewModelScope.launch {
            Log.e("yt007", "uploadPost:>>> 1111111", )
            useCases.postPosts(
                userId,
                postImage,
                postDescription,
                time
            ).collect {
                uploadPost.value = it
            }
        }
    }


}