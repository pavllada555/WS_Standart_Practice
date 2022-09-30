package com.example.mikopizza_wearos.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.mikopizza_wearos.NavigationTree
import com.example.mikopizza_wearos.screens.loginsUtils.LoginViewModel
import com.example.mikopizza_wearos.screens.mainUtils.MenuViewModel

@Composable
fun ApplicationScreen() {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = NavigationTree.Login.name
    ) {
        composable(NavigationTree.Splash.name) { SplashScreen(navController) }
        composable(NavigationTree.Login.name) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(loginViewModel = loginViewModel, navController = navController)
        }
        composable("${NavigationTree.Main.name}/{email}") { backStackEntry ->
            val menuViewModel = hiltViewModel<MenuViewModel>()
            MainScreen(backStackEntry.arguments?.getString("email").orEmpty(), menuViewModel)
        }
    }
}