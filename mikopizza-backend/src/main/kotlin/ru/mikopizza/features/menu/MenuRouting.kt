package ru.mikopizza.features.menu

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*


fun Application.configureMenuRouting() {

    routing {
        post("/menu/search") {
            MenuController(call).performSearch()

        }

        post("/menu/create") {
            MenuController(call).createMenu()
        }
    }
}