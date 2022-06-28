package ru.mikopizza.features.menu

import kotlinx.serialization.Serializable

@Serializable
data class CreateMenuRequest(
    val name: String,
    val price: Int,
    val image: String,
    val category: Int
)

@Serializable
data class CreateMenuResponse(
    val name: String,
    val price: Int,
    val image: String,
    val category: Int
)