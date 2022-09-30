package com.example.mikopizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.example.mikopizza.ui.screens.ApplicationScreen
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MikopizzaTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MikopizzaTheme {

                val systemUiController = rememberSystemUiController()
                val primaryBackground = AppTheme.colors.firstBackgroundColor

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = primaryBackground,
                        darkIcons = true
                    )
                }

                ApplicationScreen()
            }
        }
    }
}