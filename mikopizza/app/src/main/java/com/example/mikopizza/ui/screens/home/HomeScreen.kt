package com.example.mikopizza.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mikopizza.common.MenuRepository
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.ui.components.TextSearch
import com.example.mikopizza.ui.screens.home.models.HomeEvent
import com.example.mikopizza.ui.screens.home.models.HomeSubState
import com.example.mikopizza.ui.screens.home.models.HomeViewState
import com.example.mikopizza.ui.screens.home.views.LoadedView
import com.example.mikopizza.ui.screens.home.views.LoadingView

private val apiService by lazy {
    ApiService.create()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {

    val products = MenuRepository.menuItems.observeAsState().value
    val viewState = homeViewModel.viewState.observeAsState(HomeViewState())

    with(viewState.value)
    {
        Column {
            TextSearch(
                query = searchValue,
                onQueryChange = {
                    homeViewModel.obtainEvent(HomeEvent.SearchChanged(it))
                },
                onClearClicked = {
                    homeViewModel.obtainEvent(HomeEvent.SearchClearClicked)
                },
                placeholder = "Поиск по меню...",
                enabled = when (homeSubState) {
                    HomeSubState.Loaded -> true
                    else -> false
                },
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )

            when (homeSubState) {
                HomeSubState.Loading -> LoadingView()
                HomeSubState.Failed -> FailedView {
                    homeViewModel.obtainEvent(HomeEvent.RetryMenuLoadingClicked)
                }
                HomeSubState.Loaded -> LoadedView(navController, this@with, homeViewModel, products)
            }
        }
    }
}