package com.example.mikopizza.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mikopizza.ui.theme.AppTheme

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    header: String,
    textFieldValue: String,
    secureText: Boolean = false,
    enabled: Boolean = true,
    onTextFieldChange: (String) -> Unit
){
    Column(modifier = modifier)
    {   Text(
        text = header,
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
            onValueChange = onTextFieldChange,
            placeholder = header,
            secureText = secureText
        )
    }
}