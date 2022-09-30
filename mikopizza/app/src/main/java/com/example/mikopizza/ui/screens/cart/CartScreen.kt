package com.example.mikopizza.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mikopizza.R
import com.example.mikopizza.common.Cart
import com.example.mikopizza.ui.screens.cart.models.CartEvent
import com.example.mikopizza.ui.theme.AppTheme
import com.example.mikopizza.ui.theme.MyFont
import com.example.mikopizza.utils.byteArrayToBmp
import java.math.BigDecimal

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
) {

    val cartState = Cart.cartItems.observeAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            cartState.value?.toList()?.sortedBy { it.first.category }?.toMap()?.forEach {
                item {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Image(
                                bitmap = byteArrayToBmp(it.key.image).asImageBitmap(),
                                modifier = Modifier
                                    .padding(start = 20.dp),
                                contentDescription = null

                            )
                            Text(
                                text = it.key.name,
                                color = AppTheme.colors.systemTextColor,
                                fontFamily = MyFont,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                            )
                            Text(
                                text = it.key.price.toString(),
                                color = AppTheme.colors.systemTextColor,
                                fontFamily = MyFont,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(start = 20.dp, top = 4.dp)
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(id = R.string.quantity) + " ${it.value}",
                                color = AppTheme.colors.systemTextColor,
                                fontFamily = MyFont,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(
                                        end = 20.dp, start = 28.dp
                                    )
                            )
                            Text(
                                text = stringResource(id = R.string.price) + "${BigDecimal(it.value) * it.key.price}",
                                color = AppTheme.colors.systemTextColor,
                                fontFamily = MyFont,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(
                                        end = 20.dp, start = 28.dp
                                    )
                            )
                            Row {
                                Button(
                                    onClick = {
                                        cartViewModel.obtainEvent(
                                            CartEvent.DecreaseItemCount(
                                                it.key
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(start = 20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = AppTheme.colors.secondBackgroundColor
                                    )
                                ) {
                                    Text(
                                        text = "-",
                                        fontSize = 16.sp,
                                        fontFamily = MyFont,
                                        fontWeight = Bold,
                                        color = AppTheme.colors.systemTextColor
                                    )
                                }
                                Button(
                                    onClick = {
                                        cartViewModel.obtainEvent(
                                            CartEvent.IncreaseItemCount(
                                                it.key
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(end = 24.dp, start = 16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = AppTheme.colors.secondBackgroundColor
                                    )
                                ) {
                                    Text(
                                        text = "+",
                                        fontSize = 16.sp,
                                        fontFamily = MyFont,
                                        fontWeight = Bold,
                                        color = AppTheme.colors.systemTextColor
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .align(Alignment.CenterHorizontally)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.paycheck_summ) + " " + cartState.value?.toList()
                        ?.map {
                            it.first.price * BigDecimal(it.second)
                        }?.sum(),
                    color = AppTheme.colors.systemTextColor,
                    fontSize = 24.sp,
                    fontFamily = MyFont,
                    fontWeight = Bold,
                )
            }
            Box(
                modifier = Modifier
                    .clickable { cartViewModel.obtainEvent(CartEvent.MakeOrderClicked) }
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = AppTheme.colors.secondBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.make_order),
                    color = AppTheme.colors.systemTextColor,
                    fontSize = 24.sp,
                    fontFamily = MyFont,
                    fontWeight = Bold,
                )
            }
        }
    }
}

private fun List<BigDecimal>.sum(): BigDecimal {
    var sum = BigDecimal(0)
    this.forEach {
        sum += it
    }
    return sum
}