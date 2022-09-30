package com.example.mikopizza.network.models.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseModel(
    val token: String
)