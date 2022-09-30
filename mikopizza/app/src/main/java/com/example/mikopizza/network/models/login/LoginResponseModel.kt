package com.example.mikopizza.network.models.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseModel(
    val token: String
)
