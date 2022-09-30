package ru.mikopizza.database.order_positions

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.mikopizza.database.menu.Menu
import ru.mikopizza.database.order.Orders


object OrderPositions : Table("order_positions") {
    private val order_id = OrderPositions.integer("order_id").references(Orders.id)
    private val menu_name = OrderPositions.varchar("menu_name", 25).references(Menu.name)
    private val menu_category = OrderPositions.integer("menu_category").references(Menu.category)
    private val quantity = OrderPositions.integer("quantity")

    fun insert(orderPositionsDTO: OrderPositionsDTO) {
        transaction {
            OrderPositions.insert {
                it[order_id] = orderPositionsDTO.order_id
                it[menu_name] = orderPositionsDTO.menu_name
                it[menu_category] = orderPositionsDTO.menu_category
                it[quantity] = orderPositionsDTO.quantity
            }
        }
    }

    fun fetchPositions(orderId: Int): List<OrderPositionsDTO>? {
        return try {
            transaction {
                OrderPositions.select(OrderPositions.order_id.eq(orderId)).toList().map {
                    OrderPositionsDTO(
                        order_id = it[order_id],
                        menu_name = it[menu_name],
                        menu_category = it[menu_category],
                        quantity = it[quantity]
                    )
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}