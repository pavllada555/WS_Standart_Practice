package com.example.mikopizza.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont
import com.example.mikopizza.utils.PhoneNumberVisualTransformation

enum class TextVisuals {
    Text, Password, PhoneNumber
}

@Composable
fun DTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean = true,
    textVisuals: TextVisuals = TextVisuals.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,

    ) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = MyFont,
                style = TextStyle(
                    color = AppTheme.colors.authorizationTextColor,
                    fontSize = 12.sp
                )

            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = AppTheme.colors.secondBackgroundColor,
            errorIndicatorColor = AppTheme.colors.secondBackgroundColor,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
        ),
        visualTransformation = when (textVisuals) {
            TextVisuals.Text -> VisualTransformation.None
            TextVisuals.Password -> PasswordVisualTransformation()
            TextVisuals.PhoneNumber -> PhoneNumberVisualTransformation()
        }
    )
}