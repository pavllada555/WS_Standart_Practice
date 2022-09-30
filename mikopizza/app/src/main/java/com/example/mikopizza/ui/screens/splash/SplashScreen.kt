package com.example.mikopizza.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.mikopizza.R
import com.example.mikopizza.navigation.NavigationTree
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont

@Composable
fun SplashScreen(navController: NavController) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(top = 200.dp, start = 115.dp, end = 55.dp, bottom = 50.dp)
    )
    Text(
        text = "Miko pizza",
        fontSize = 32.sp,
        fontFamily = MyFont,
        color = AppTheme.colors.systemTextColor,
        modifier = Modifier
            .padding(top = 400.dp, start = 100.dp),
    )
    LaunchedEffect(key1 = Unit, block = {
        navController.navigate(NavigationTree.Login.name)
    })
}
