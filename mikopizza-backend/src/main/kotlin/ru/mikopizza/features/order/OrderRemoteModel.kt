package ru.mikopizza.features.order


import kotlinx.serialization.Serializable
import ru.mikopizza.features.menu.MenuResponse

@Serializable
data class ItemInfo(
    val item_name: String,
    val item_category: Int,
    val quantity: Int
)

@Serializable
data class OrderReceiveModel(
    val itemList: List<ItemInfo>,
    val userEmail: String
)

@Serializable
data class OrderEmailModel(
    val userEmail: String
)

@Serializable
data class OrderIdModel(
    val orderId: Int
)

@Serializable
data class OrderResponseModel(
    val id: Int?,
    val status: Int
)

@Serializable
data class OrderFetchModel(
    val id: Int,
    val status: Int,
    val menuItems: List<MenuResponse>
)

@Serializable
data class OrderListModel(
    val orderList: List<OrderFetchModel>?,
)