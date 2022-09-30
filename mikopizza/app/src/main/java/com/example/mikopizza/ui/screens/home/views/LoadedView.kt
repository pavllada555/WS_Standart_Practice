package com.example.mikopizza.ui.screens.home.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mikopizza.navigation.NavigationTree
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.ui.components.MenuCard
import com.example.mikopizza.ui.screens.home.HomeViewModel
import com.example.mikopizza.ui.screens.home.models.HomeEvent
import com.example.mikopizza.ui.screens.home.models.HomeViewState
import com.example.mikopizza.ui.screens.home.models.ItemClickAction
import com.example.mikopizza.utils.byteArrayToBmp

@Composable
fun LoadedView(
    navController: NavController,
    viewState: HomeViewState,
    viewModel: HomeViewModel,
    products: List<MenuResponseModel>?
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxSize()
    )
    {
        products?.let {
            items(it.filter { product ->
                product.name.contains(viewState.searchValue, ignoreCase = true)
            }
            ) { product ->
                MenuCard(
                    title = product.name,
                    image_bmp = byteArrayToBmp(product.image),
                    price = product.price,
                    onClick = { viewModel.obtainEvent(HomeEvent.MenuItemClicked(product.name)) }
                )
            }
        }
    }
    LaunchedEffect(key1 = viewState.itemClickAction) {
        when (val action = viewState.itemClickAction) {
            is ItemClickAction.OpenItemPicker
            -> {
                navController.navigate("${NavigationTree.MenuItem.name}/${action.item_name}")
            }
            else -> Unit
        }
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.obtainEvent(HomeEvent.ItemActionInvoked)
        }
    }
}