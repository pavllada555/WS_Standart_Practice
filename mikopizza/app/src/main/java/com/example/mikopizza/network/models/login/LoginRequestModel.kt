package com.example.mikopizza.network.models.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModel(
    val email: String,
    val password: String
)
