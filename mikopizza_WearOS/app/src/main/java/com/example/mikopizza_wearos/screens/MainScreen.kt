package com.example.mikopizza_wearos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*
import com.example.mikopizza_wearos.screens.mainUtils.MenuViewModel
import com.example.mikopizza_wearos.screens.mainUtils.OrderCard
import com.example.mikopizza_wearos.theme.WearAppColorPalette

@Composable
fun MainScreen(username: String, viewModel: MenuViewModel) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {
            if (!listState.isScrollInProgress) {
                TimeText()
            }
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState
            )

        },
        modifier = Modifier
            .background(WearAppColorPalette.primary)

    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
            state = listState
        ) {
            item { OrderCard(title = "title", count = 1) }
            item { OrderCard(title = "title", count = 1) }
            item { OrderCard(title = "title", count = 1) }
            /*TODO*/
//            items(items = viewModel.orders.value!!.toList()) { order ->
//                order.menuItems.forEach { menuItem ->
//                    OrderCard(title = menuItem.name, count = menuItem.quantity)
//                }
//
//            }
        }
    }
}