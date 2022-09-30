package com.example.mikopizza_wearos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.mikopizza_wearos.NavigationTree
import com.example.mikopizza_wearos.R

@Composable
fun SplashScreen(navController: NavController) {
    Image(
        painter = painterResource(R.drawable.splashscreen),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    LaunchedEffect(
        key1 = Unit,
        block = {
            navController.navigate(NavigationTree.Login.name)
        }
    )
}