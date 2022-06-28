package com.example.mikopizza.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.ui.theme.AppTheme

@Composable
fun DTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange : (String)->Unit,
    placeholder : String,
    enabled: Boolean = true,
    secureText: Boolean = false,
) {
    TextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        placeholder = {
        Text(
            text = placeholder,
            style = TextStyle(
                color = AppTheme.colors.authorizationTextColor,
                fontSize = 12.sp
            )
        )
    }, shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppTheme.colors.secondBackgroundColor,
            disabledIndicatorColor = AppTheme.colors.secondBackgroundColor,
            errorIndicatorColor = AppTheme.colors.secondBackgroundColor,
            focusedIndicatorColor = AppTheme.colors.secondBackgroundColor,
            unfocusedIndicatorColor = AppTheme.colors.secondBackgroundColor
    ),
        visualTransformation = if(secureText) PasswordVisualTransformation() else VisualTransformation.None,
    )
}