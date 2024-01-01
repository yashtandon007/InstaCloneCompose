package com.example.instaclonecompose.feature_auth.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.instaclonecompose.R
import com.example.instaclonecompose.feature_auth.domain.model.User
import com.example.instaclonecompose.feature_auth.presentation.components.PostSection
import com.example.instaclonecompose.feature_auth.presentation.components.RoundedImage
import com.example.instaclonecompose.feature_auth.presentation.nav.BottomNav
import com.example.instaclonecompose.feature_auth.presentation.user.UserViewModel
import com.example.instaclonecompose.util.Response


@Composable
fun ProfileScreen(
    navController: NavController, userViewModel: UserViewModel? = null
) {

    when (val response = userViewModel?.getUserData?.value) {
        is Response.Error -> {

            Log.e("yt007", "ProfileScreen:error ")
        }

        is Response.Loading -> {
            Log.e("yt007", "ProfileScreen:Loading ")
            CircularProgressIndicator()
        }

        is Response.Success -> {
            Log.e("yt007", "ProfileScreen:Success ")
            val data = response.data
            data?.let {
                Success(it)
            }
        }

        else -> {}
    }

    LaunchedEffect(key1 = true) {
        userViewModel?.getUserData()
//        userViewModel?.setUserData(
//            name = "yashtandon",
//            bio = "Software Developer 3",
//            url = "httptestcom",
//            userName = "yt007"
//        )
    }

    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {

        }
        BottomNav(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun Success(
    user: User = User(
        userName = "yt007",
        name = "Yash Tandon",
        bio = "dfdsfsf asdasd dfdsfsf asdasd dfdsfsf asdasd dfdsfsf asdasd dfdsfsf asdasd dfdsfsf asdasd dfdsfsf asdasd",
        url = "ahsjahsjah"
    )
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Header(user.userName)
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    RoundedImage(
                        imageUrl = user.imageUrl, modifier = Modifier.weight(0.8f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.weight(3f)
                    ) {

                        ProfileStat(
                            count = 12, text = "Following"
                        )
                        ProfileStat(
                            count = 12, text = "Following"
                        )
                        ProfileStat(
                            count = 12, text = "Following"
                        )
                    }
                }

                MyProfile(
                    displayName = user.name, bio = user.bio, url = user.url
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(name: String) {
    TopAppBar(modifier = Modifier.shadow(
        elevation = 10.dp
    ), title = {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 25.sp
        )
    }, actions = {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_box_24),
            contentDescription = "Create",
            tint = Color.Black,
            modifier = Modifier.width(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            painter = painterResource(id = R.drawable.baseline_menu_24),
            contentDescription = "More",
            tint = Color.Black,
            modifier = Modifier.width(30.dp)
        )
    })
}

@Composable
fun MyProfile(
    displayName: String, bio: String, url: String
) {

    val selectedIndex = remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = displayName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = bio, lineHeight = 20.sp, maxLines = 2)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = url,
            lineHeight = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF3D3D91)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(5.dp)
                .clickable { }) {
            Text(text = "Edit Profile", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TabView(tabModles = listOf(
            TabModel(image = painterResource(id = R.drawable.baseline_menu_24), text = "Posts"),
            TabModel(image = painterResource(id = R.drawable.baseline_menu_24), text = "Reels"),
            TabModel(image = painterResource(id = R.drawable.baseline_menu_24), text = "Igtv")
        )){
            selectedIndex.value = it
        }

        when(selectedIndex.value){
            0->{
                PostSection()
            }
            1->{

            }
            2->{

            }
        }

    }
}

data class TabModel(
    val image: Painter,
    val text:String
)

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    tabModles:List<TabModel>,
    onSelected:(selectedIndex:Int)->Unit
) {
    val selectedTabIndex = remember {
        mutableStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        modifier =modifier.background(Color.Transparent),
        containerColor = Color.Transparent,
        tabs = {
            tabModles.forEachIndexed{index,item->
                Tab(selected = selectedTabIndex.value == index, onClick = {
                    selectedTabIndex.value = index
                    onSelected(index)
                },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color(0xFF7777777),
                    icon = {
                        Icon(painter = item.image, contentDescription = "icon")
                    }
                )
            }
        }
    )
}
@Composable
fun ProfileStat(count: Int, text: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewProfile() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}