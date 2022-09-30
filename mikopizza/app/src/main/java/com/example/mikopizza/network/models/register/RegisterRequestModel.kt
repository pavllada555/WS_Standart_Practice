package com.example.mikopizza.network.models.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestModel(
    val fullName: String,
    val password: String,
    val email: String,
    val phoneNumber: String
)