package ru.mikopizza.features.menu

import kotlinx.serialization.Serializable

@Serializable
data class FetchMenuRequest(
    val searchQuery: String
)