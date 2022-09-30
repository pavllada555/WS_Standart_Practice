package com.example.mikopizza.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont

@Composable
fun ItemCounter(
    count: Int,
    onDecreaseClicked: () -> Unit,
    onIncreaseClicked: () -> Unit,
) {

    Row {
        Button(
            onClick = onDecreaseClicked,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.activeTextColor),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(60.dp)
                .height(60.dp)
        ) {
            Text(
                text = "-",
                fontSize = 24.sp,
                fontFamily = MyFont,
                color = AppTheme.colors.systemTextColor
            )
        }
        Text(
            text = count.toString(),
            color = AppTheme.colors.systemTextColor,
            fontSize = 24.sp,
            fontFamily = MyFont,
            fontWeight = Bold,
            modifier = Modifier
                .padding(top = 34.dp, start = 20.dp)
        )
        Button(
            onClick = onIncreaseClicked,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.activeTextColor),
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
                .width(60.dp)
                .height(60.dp)
        ) {
            Text(
                text = "+",
                fontSize = 24.sp,
                fontFamily = MyFont,
                color = AppTheme.colors.systemTextColor)
        }
    }
}