package ru.mikopizza

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.mikopizza.features.login.configureLoginRouting
import ru.mikopizza.features.menu.configureMenuRouting
import ru.mikopizza.features.order.configureOrderRouting
import ru.mikopizza.features.register.configureRegisterRouting
import ru.mikopizza.plugins.*


fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/mikopizza", driver = "org.postgresql.Driver",
        user = "postgres", password = "admin"
    )
    embeddedServer(CIO, port = 8080, host = "localhost") {
        configureMenuRouting()
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSerialization()
        configureOrderRouting()
    }.start(wait = true)
}
