package ru.mikopizza.features.order

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureOrderRouting() {

    routing {
        post ("/order/fetch_orders") {
            OrderController(call).fetchOrders()
        }
        post("/order/make_order") {
            OrderController(call).makeOrder()
        }
        post("/order/update_order_status") {
            OrderController(call).updateOrderStatus()
        }
    }
}