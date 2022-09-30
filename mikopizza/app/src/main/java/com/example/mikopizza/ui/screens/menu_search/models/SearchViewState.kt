package com.example.mikopizza.ui.screens.menu_search.models

enum class SearchSubState {
    Start
}

data class SearchViewState(
    val query: String = "",
    val searchResults: List<String> = emptyList(),
    val refreshing: Boolean = false,
    )