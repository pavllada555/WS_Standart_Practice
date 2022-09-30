package com.example.mikopizza.network

import com.example.mikopizza.network.models.login.LoginRequestModel
import com.example.mikopizza.network.models.login.LoginResponseModel
import com.example.mikopizza.network.models.login.LoginResult
import com.example.mikopizza.network.models.menuorders.*
import com.example.mikopizza.network.models.register.RegisterRequestModel
import com.example.mikopizza.network.models.register.RegisterResponseModel
import com.example.mikopizza.network.models.register.RegisterResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getProducts(query: String): List<MenuResponseModel> {
        val response = client.post {
            header("Bearer-Authorization", "")
            url(ApiRoutes.MENU_SEARCH)
            contentType(ContentType.Application.Json)
            setBody(MenuRequestModel(query))
        }
        val menuList = response.body<List<MenuResponseModel>>()

        return try {
            menuList

        }  catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            emptyList()
        }
    }

    override suspend fun tryLogin(email: String, password: String): LoginResult {
        try {
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
        } catch (e: Exception) {
            return LoginResult.SomethingWentWrong
        }
    }

    override suspend fun tryRegister(
        email: String,
        password: String,
        phoneNumber: String,
        fullName: String
    ): RegisterResult {
        return try {
            val response = client.post {
                url(ApiRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                setBody(
                    RegisterRequestModel(
                        fullName = fullName,
                        email = email,
                        password = password,
                        phoneNumber = phoneNumber
                    )
                )
            }


            when (response.status) {
                HttpStatusCode.OK -> RegisterResult.Ok(token = response.body<RegisterResponseModel>().token)
                HttpStatusCode.BadRequest -> RegisterResult.EmailIsNoValid
                HttpStatusCode.Conflict -> RegisterResult.UserAlreadyExists
                else -> RegisterResult.SomethingWentWrong
            }
        } catch (e: Exception) {
            RegisterResult.SomethingWentWrong
        }
    }

    override suspend fun tryMakeOrder(itemMap: Map<MenuResponseModel, Int>): OrderResult {
        var itemInfoList = mutableListOf<ItemInfo>()
        itemMap.forEach {
            itemInfoList.add(
                ItemInfo(
                    item_name = it.key.name,
                    item_category = it.key.category,
                    quantity = it.value
                )
            )
        }
        return try {
            val response = client.post {
                url(ApiRoutes.MAKE_ORDER)
                contentType(ContentType.Application.Json)
                setBody(
                    OrderRequestModel(itemInfoList)
                )
            }
            when (response.status) {
                HttpStatusCode.OK -> OrderResult.Ok(orderResponseModel = response.body<OrderResponseModel>())
                else -> OrderResult.SomethingWentWrong
            }
        } catch (e: Exception) {
            OrderResult.SomethingWentWrong
        }
    }
}