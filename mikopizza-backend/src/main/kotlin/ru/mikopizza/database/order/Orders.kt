package ru.mikopizza.database.order

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Orders : IdTable<Int>("orders") {
    override val id = Orders.integer("id").autoIncrement().entityId()
    private val status = Orders.integer("status")
    private val user_email = Orders.varchar("user_email", 25)

    fun insertAndGetId(orderDTO: OrderDTO): Int {
        var id = transaction {
            Orders.insertAndGetId {
                it[status] = orderDTO.status
                it[user_email] = orderDTO.user_email
            }
        }
        return id.value
    }

    fun fetchOrders(orderEmail: String): List<OrderDTO>? {
        return try {
            transaction {
                val orderModels = Orders.select{Orders.user_email.eq(orderEmail)}
                val orderList = mutableListOf<OrderDTO>()
                orderModels.forEach {
                    orderList.add(
                        OrderDTO(
                            id = it[Orders.id].value,
                            status = it[status],
                            user_email = it[user_email]
                        )
                    )
                }
                orderList
            }
        } catch (e: Exception) {
            null
        }
    }

    fun updateStatus(orderId: Int): Int {

        return try {
            transaction {
                val orderModel = Orders.select(Orders.id eq orderId).single()

                Orders.update(where = { Orders.id eq orderId }) {
                    with(SqlExpressionBuilder) {
                        it.update(Orders.status, Orders.status + 1)
                    }
                }
                Orders.select(Orders.id eq orderId).single()[Orders.status]
            }
        } catch (e: Exception) {
            -1
        }
    }
}