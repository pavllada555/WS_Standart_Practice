package ru.mikopizza.features.menu

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import ru.mikopizza.database.menu.Menu
import io.ktor.server.response.*
import ru.mikopizza.database.menu.mapToCreateMenuResponse
import ru.mikopizza.database.menu.mapToMenuDTO
import ru.mikopizza.features.utils.TokenCheck

class MenuController(private val call: ApplicationCall) {
    suspend fun performSearch() {
        val receive = call.receive<FetchMenuRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
            if (receive.searchQuery.isBlank()) {
                call.respond(Menu.fetchFullMenu())
            } else {
                call.respond(
                    Menu.fetchFullMenu().filter { it.name.contains(receive.searchQuery, ignoreCase = true) })
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun createMenu() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateMenuRequest>()
            val menu = request.mapToMenuDTO()
            Menu.insert(menu)
            call.respond(menu.mapToCreateMenuResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}