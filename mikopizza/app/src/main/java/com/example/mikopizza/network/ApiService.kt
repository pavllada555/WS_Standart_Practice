package com.example.mikopizza.network

import com.example.mikopizza.network.models.login.LoginResult
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.network.models.menuorders.OrderResult
import com.example.mikopizza.network.models.register.RegisterResult
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

interface ApiService {
    suspend fun getProducts(query: String): List<MenuResponseModel>

    suspend fun tryLogin(
        email: String,
        password: String
    ): LoginResult

    suspend fun tryRegister(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): RegisterResult

    suspend fun tryMakeOrder(
        itemMap: Map<MenuResponseModel, Int>
    ): OrderResult

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    // Logging
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    // JSON
                    install(ContentNegotiation) {
                        json()
                        //or serializer = KotlinxSerializer()
                    }
                    // Timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = 300000L
                        connectTimeoutMillis = 300000L
                        socketTimeoutMillis = 300000L
                    }
                    // Apply to all requests
                    defaultRequest {
                        // Parameter("api_key", "some_api_key")
                        // Content Type
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }

        private val json = kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }
}