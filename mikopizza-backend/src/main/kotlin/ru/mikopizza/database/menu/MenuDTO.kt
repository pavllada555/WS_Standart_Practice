package ru.mikopizza.database.menu

import ru.mikopizza.features.menu.CreateMenuRequest
import ru.mikopizza.features.menu.CreateMenuResponse

data class MenuDTO
    (
    var name: String,
    val price: Int,
    val image: String,
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