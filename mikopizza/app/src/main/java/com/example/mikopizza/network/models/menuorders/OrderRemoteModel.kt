package com.example.mikopizza.network.models.menuorders

import kotlinx.serialization.Serializable

@Serializable
data class ItemInfo(
    val item_name: String,
    val item_category: Int,
    val quantity: Int
)

@Serializable
data class OrderRequestModel(
    val itemList: List<ItemInfo>
)

@Serializable
data class OrderIdModel(
    val orderId: Int
)

@Serializable
data class OrderResponseModel(
    val id: Int,
    val status: Int
)