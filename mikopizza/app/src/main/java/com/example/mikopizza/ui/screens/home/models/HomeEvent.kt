package com.example.mikopizza.ui.screens.home.models

sealed class HomeEvent {
    object ItemActionInvoked : HomeEvent()
    object SearchClearClicked : HomeEvent()
    object RetryMenuLoadingClicked : HomeEvent()
    data class MenuItemClicked(val value: String) : HomeEvent()
    data class SearchChanged(val value: String) : HomeEvent()
}