package com.example.mikopizza.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.mikopizza.ui.components.BottomNavigationBar
import com.example.mikopizza.ui.components.Navigation
import com.example.mikopizza.ui.theme.AppTheme

@Composable
fun MainScreen(username: String) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = AppTheme.colors.thirdBackgroundColor
    ) // Set background color to avoid the white flashing when you switch between screens

}