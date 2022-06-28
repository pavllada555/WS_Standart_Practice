package ru.mikopizza.features.menu

import kotlinx.serialization.Serializable

@Serializable
data class FetchMenuRequest(
    val searchQuery: String
)

@Serializable
data class FetchMenuResponse(
    val menu: List<MenuResponse>
)

@Serializable
data class  MenuResponse(
    val name: String,
    val price: Int,
    val image: String,
    val category: Int
)