package ru.mikopizza.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.mikopizza.database.tokens.TokenDTO
import ru.mikopizza.database.tokens.Tokens
import ru.mikopizza.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.email)
        if (userDTO == null)
            call.respond(HttpStatusCode.Conflict, "User not found")
        else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowId = UUID.randomUUID().toString(), login = receive.email, token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            } else
                call.respond(HttpStatusCode.BadRequest, "Invalid Password")
        }

    }
}