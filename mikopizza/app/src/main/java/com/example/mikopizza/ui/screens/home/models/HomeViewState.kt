package com.example.mikopizza.ui.screens.home.models

enum class HomeSubState {
    Loading, Failed, Loaded
}

sealed class ItemClickAction {
    data class OpenItemPicker(val item_name: String) : ItemClickAction()
    object None : ItemClickAction()
}

data class HomeViewState(
    val homeSubState: HomeSubState = HomeSubState.Loading,
    val searchValue: String = "",
    val itemClickAction: ItemClickAction = ItemClickAction.None
)