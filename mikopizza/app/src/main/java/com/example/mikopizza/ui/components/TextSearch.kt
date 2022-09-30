package com.example.mikopizza.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.R
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont

@Composable
fun TextSearch(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearClicked: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Search...",
) {

    TextField(
        singleLine = true,
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = MyFont,
                style = TextStyle(
                    color = AppTheme.colors.systemTextColor,
                    fontSize = 16.sp
                )

            )
        },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(100),
        trailingIcon = {
            when (query.isEmpty()) {
                true -> Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp),
                    tint = AppTheme.colors.activeTextColor,
                )
                else -> Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_clear),
                    contentDescription = "cancel search",
                    tint = AppTheme.colors.activeTextColor,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
                        .clickable(onClick = onClearClicked)
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = AppTheme.colors.authorizationTextColor,
            textColor = AppTheme.colors.systemTextColor
        )
    )
}