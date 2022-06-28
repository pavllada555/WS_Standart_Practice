package ru.mikopizza.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.serialization.kotlinx.json.*

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json()
    }

}