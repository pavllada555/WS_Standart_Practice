package com.example.mikopizza.network.models.menuorders

import com.example.mikopizza.db.DatabaseMenuItem
import com.example.mikopizza.utils.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal


@Serializable
data class MenuResponseModel(
    val name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int
)

fun List<MenuResponseModel>.asDatabaseModel(): List<DatabaseMenuItem> {
    return map {
        DatabaseMenuItem(
            name = it.name,
            price = it.price,
            image = it.image,
            category = it.category
        )
    }
}