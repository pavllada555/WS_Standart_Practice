package com.example.mikopizza_wearos.network.models

import com.example.mikopizza_wearos.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OrderEmailModel(
    val userEmail: String,
)

@Serializable
data class MenuResponse(
    val name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int,
    val quantity: Int
)

@Serializable
data class OrderFetchModel(
    val id: Int,
    val status: Int,
    val menuItems: List<MenuResponse>
)

@Serializable
data class OrderListModel(
    val orderList: List<OrderFetchModel>
)