package ru.mikopizza.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.mikopizza.database.tokens.TokenDTO
import ru.mikopizza.database.tokens.Tokens
import ru.mikopizza.database.users.UserDTO
import ru.mikopizza.database.users.Users
import ru.mikopizza.features.utils.isValidEmail
import java.util.*

class RegisterController(val call: ApplicationCall) {
    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }
        val userDTO = Users.fetchUser(registerReceiveRemote.fullName)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exist")
        } else {
            val token = UUID.randomUUID().toString()
            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.fullName,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        phone_number = registerReceiveRemote.phoneNumber
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }
            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(), login = registerReceiveRemote.fullName, token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}