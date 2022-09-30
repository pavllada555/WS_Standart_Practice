package com.example.mikopizza_wearos.network

import com.example.mikopizza_wearos.network.models.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun tryLogin(email: String, password: String): LoginResult {
        val response = client.post {
            url(ApiRoutes.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(LoginRequestModel(email, password))
        }
        return when (response.status) {
            HttpStatusCode.OK -> LoginResult.Ok(token = response.body<LoginResponseModel>().token)
            HttpStatusCode.Conflict -> LoginResult.UserNotFound
            HttpStatusCode.BadRequest -> LoginResult.InvalidPassword
            else -> LoginResult.SomethingWentWrong
        }
    }

    override suspend fun tryGetOrders(email: String): OrderListModel? {
        val response = client.post{
            url(ApiRoutes.FETCH_ORDERS)
            contentType(ContentType.Application.Json)
            setBody(OrderEmailModel(email))
        }
        return when (response.status) {
            HttpStatusCode.OK -> response.body<OrderListModel>()
            else -> null
        }
    }
}