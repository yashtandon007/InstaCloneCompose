package com.example.instaclonecompose.feature_auth.domain.model

data class User(

    var useId:String = "",
    var userName:String = "",
    var name:String = "",
    var email:String = "",
    var password:String = "",
    var imageUrl:String = "",
    var bio:String = "",
    var url:String = "",
    var totalPosts:String = "",
    var followers: List<String> = emptyList(),
    var following: List<String> = emptyList()

)
