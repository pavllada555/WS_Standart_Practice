package ru.mikopizza.features.menu

import kotlinx.serialization.Serializable
import ru.mikopizza.features.utils.BigDecimalSerializer
import java.math.BigDecimal

@Serializable
data class CreateMenuRequest(
    val name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int
)

@Serializable
data class CreateMenuResponse(
    val name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int
)