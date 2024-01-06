package com.example.instaclonecompose.feature_auth.presentation.post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonecompose.feature_auth.domain.model.Post
import com.example.instaclonecompose.feature_auth.domain.use_cases.post.PostUseCases
import com.example.instaclonecompose.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val useCases: PostUseCases
) : ViewModel() {

    val posts = mutableStateOf<Response<List<Post>>>(Response.Loading)
    val uploadPost = mutableStateOf<Response<Boolean>>(Response.Success(false))

    fun getPosts(
        userId: String
    ) {
        viewModelScope.launch {
            useCases.getPosts(userId).collect {
                posts.value = it
            }
        }
    }

    fun postPosts(
        userId:String,
        postId:String,
        postImage:String,
        postDescription:String,
        time:String
    ) {
        viewModelScope.launch {
            useCases.postPosts(
                userId,
                postId,
                postImage,
                postDescription,
                time
            ).collect {
                uploadPost.value = it
            }
        }
    }


}