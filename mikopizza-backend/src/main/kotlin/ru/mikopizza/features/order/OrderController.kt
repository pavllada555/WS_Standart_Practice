package ru.mikopizza.features.order

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.mikopizza.database.menu.Menu
import ru.mikopizza.database.order.OrderDTO
import ru.mikopizza.database.order.OrderStatus
import ru.mikopizza.database.order.Orders
import ru.mikopizza.database.order_positions.OrderPositions
import ru.mikopizza.database.order_positions.OrderPositionsDTO
import ru.mikopizza.features.menu.MenuResponse


class OrderController(private val call: ApplicationCall) {
    suspend fun makeOrder() {
        val receive = call.receive<OrderReceiveModel>()
        val order_id = Orders.insertAndGetId(
            OrderDTO(
                id = null,
                status = OrderStatus.ACCEPTED,
                user_email = receive.userEmail
            )
        )
        receive.itemList.forEach {
            OrderPositions.insert(
                OrderPositionsDTO(
                    order_id = order_id,
                    menu_name = it.item_name,
                    menu_category = it.item_category,
                    quantity = it.quantity,
                )
            )
        }
        call.respond(
            OrderResponseModel(
                id = order_id,
                status = OrderStatus.ACCEPTED
            )
        )
    }

    suspend fun updateOrderStatus() {
        val receive = call.receive<OrderIdModel>()
        val order_id = receive.orderId
        call.respond(
            OrderResponseModel(
                id = order_id,
                status = Orders.updateStatus(order_id)
            )
        )
    }

    suspend fun fetchOrders() {
        val receive = call.receive<OrderEmailModel>()
        val orderDtoList = Orders.fetchOrders(receive.userEmail)
        val orderFetchList = mutableListOf<OrderFetchModel>()
        orderDtoList?.forEach {
            val orderPositionsDTOList = OrderPositions.fetchPositions(it.id!!)
            val menuItemsList = mutableListOf<MenuResponse>()
            orderPositionsDTOList?.forEach { orderPositionsDTO ->
                val menuDTO = Menu.fetchFullMenu().filter {
                    it.name.equals(orderPositionsDTO.menu_name)
                }
                menuItemsList.add(
                    MenuResponse(
                        name = menuDTO.first().name,
                        price = menuDTO.first().price,
                        category = menuDTO.first().category,
                        image = menuDTO.first().image,
                        quantity = orderPositionsDTO.quantity

                    )
                )
            }
            orderFetchList.add(
                OrderFetchModel(
                    id = it.id,
                    status = it.status,
                    menuItems = menuItemsList
                )
            )
        }
        call.respond(
            OrderListModel(
                orderList = orderFetchList
            )
        )
    }

}