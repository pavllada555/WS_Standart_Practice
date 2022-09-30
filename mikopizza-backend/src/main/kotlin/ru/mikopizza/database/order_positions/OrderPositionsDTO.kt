package ru.mikopizza.database.order_positions

@kotlinx.serialization.Serializable
data class OrderPositionsDTO(
    val order_id: Int,
    val menu_name: String,
    val menu_category: Int,
    val quantity: Int
)