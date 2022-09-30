package com.example.mikopizza.ui.screens.menu_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.R
import com.example.mikopizza.common.Cart
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.ui.components.ItemCounter
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont
import com.example.mikopizza.utils.byteArrayToBmp

@Composable
fun MenuItemScreen(
    menuItem: MenuResponseModel
) {

    val viewState = Cart.cartItems.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            bitmap = byteArrayToBmp(menuItem.image).asImageBitmap(),
            contentDescription = "product image"
        )
        Text(
            text = menuItem.name,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp),
            fontFamily = MyFont,
            color = AppTheme.colors.systemTextColor
        )




        if (viewState.value?.getOrDefault(menuItem, 0) == 0) {
            Button(
                onClick = {
                    Cart.addItem(menuItem)
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(60.dp)
                    .width(180.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.activeTextColor),

                ) {
                Text(
                    text = stringResource(id = R.string.add_item),
                    color = AppTheme.colors.systemTextColor,
                    fontSize = 24.sp,
                    fontFamily = MyFont,
                )
            }
        } else {
            ItemCounter(
                count = viewState.value!!.getOrDefault(menuItem, 0),
                onDecreaseClicked = {
                    Cart.removeItem(menuItem)
                },
                onIncreaseClicked = {
                    Cart.addItem(menuItem)
                }
            )
        }
        Button(
            onClick = {
                Cart.removeItem(menuItem = menuItem, true)
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(60.dp)
                .width(180.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.activeTextColor),
        ) {
            Text(
                text = stringResource(id = R.string.remove_items),
                color = AppTheme.colors.systemTextColor,
                fontSize = 24.sp,
                fontFamily = MyFont,
            )
        }
    }
}