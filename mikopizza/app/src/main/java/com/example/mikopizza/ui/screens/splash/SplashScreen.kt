package com.example.mikopizza.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.mikopizza.navigation.NavigationTree

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(key1 = Unit, block ={
        navController.navigate(NavigationTree.Login.name)
    })
}