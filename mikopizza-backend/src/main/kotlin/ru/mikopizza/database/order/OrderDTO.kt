package ru.mikopizza.database.order

@kotlinx.serialization.Serializable
data class OrderDTO(
    val id: Int?,
    val status: Int,
    val user_email: String
)