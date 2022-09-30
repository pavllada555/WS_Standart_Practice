package com.example.mikopizza_wearos.network.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModel(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseModel(
    val token: String
)

sealed class LoginResult {
    data class Ok(val token: String) : LoginResult()
    object UserNotFound : LoginResult()
    object InvalidPassword : LoginResult()
    object SomethingWentWrong : LoginResult()
}