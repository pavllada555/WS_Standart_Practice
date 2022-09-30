package com.example.mikopizza.ui.screens.home.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.ui.theme.AppTheme

@Composable
fun LoadingView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
    {
        CircularProgressIndicator(
            strokeWidth = 2.dp,
            color = AppTheme.colors.activeTextColor
        )
        Text(
            text = "Loading menu...",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)

        )
    }
}