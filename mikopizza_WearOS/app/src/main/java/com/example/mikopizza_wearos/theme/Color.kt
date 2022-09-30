package com.example.mikopizza_wearos.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val backGroundColor = Color(0xFFA39391)
val textColor = Color(0xFFFFFFFF)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

val WearAppColorPalette: Colors = Colors(
    primary = backGroundColor,
    primaryVariant = textColor,
    secondary = Teal200,
    secondaryVariant = Teal200,
    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)
