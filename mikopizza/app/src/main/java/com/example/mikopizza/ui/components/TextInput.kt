package com.example.mikopizza.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    header: String,
    textFieldValue: String,
    secureText: Boolean = false,
    textVisuals : TextVisuals = TextVisuals.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true,
    onTextFieldChange: (String) -> Unit
) {
    Column(modifier = modifier)
    {
        Text(
            text = header,
            fontFamily = MyFont,
            color = AppTheme.colors.authorizationTextColor,
            fontWeight = FontWeight.Medium
        )
        DTextField(
            enabled = enabled,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .height(50.dp),
            value = textFieldValue,
            textVisuals = textVisuals,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            onValueChange = onTextFieldChange,
            placeholder = header,
        )
    }
}