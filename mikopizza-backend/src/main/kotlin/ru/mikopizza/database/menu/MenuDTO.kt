package ru.mikopizza.database.menu

import ru.mikopizza.features.menu.CreateMenuRequest
import ru.mikopizza.features.menu.CreateMenuResponse
import java.math.BigDecimal
import kotlinx.serialization.Serializable
import ru.mikopizza.features.utils.BigDecimalSerializer

@Serializable
data class MenuDTO
    (
    var name: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int
)

fun CreateMenuRequest.mapToMenuDTO(): MenuDTO =
    MenuDTO(
        name = name,
        price = price,
        image = image,
        category = category
    )

fun MenuDTO.mapToCreateMenuResponse(): CreateMenuResponse =
    CreateMenuResponse(
        name = name,
        price = price,
        image = image,
        category = category
    )