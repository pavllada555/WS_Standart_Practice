package com.example.mikopizza.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val firstBackgroundColor : Color,
    val secondBackgroundColor : Color,
    val thirdBackgroundColor : Color,
    val authorizationTextColor : Color,
    val systemTextColor : Color,
    val activeTextColor: Color,
    val buttonColor : Color,
)

val lightPalette = Colors(
    firstBackgroundColor = Color(0xFFECD6C7),
    secondBackgroundColor = Color(0xFFA39391),
    thirdBackgroundColor = Color(0xFFF2F2F2),
    authorizationTextColor = Color(0xFFFFFFFF),
    systemTextColor = Color(0xFF000000),
    activeTextColor = Color(0xFFFADB01),
    buttonColor = Color(0x32FFFFFF)
)
