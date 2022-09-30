package ru.mikopizza.features.menu

import java.math.BigDecimal
import kotlinx.serialization.Serializable
import ru.mikopizza.features.utils.BigDecimalSerializer

@Serializable
data class FetchMenuResponse(
    val menu: List<MenuResponse>
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