package ru.mikopizza

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.mikopizza.features.login.configureLoginRouting
import ru.mikopizza.features.menu.configureMenuRouting
import ru.mikopizza.features.register.configureRegisterRouting
import ru.mikopizza.plugins.*


fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/mikopizza", driver = "org.postgresql.Driver",
    user = "postgres", password = "admin")
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") { //TODO Find host, bc 0.0.0.0/127.0.0.1/localhost doesn't working
        configureRouting()
        configureMenuRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSerialization()
    }.start(wait = true)
}
