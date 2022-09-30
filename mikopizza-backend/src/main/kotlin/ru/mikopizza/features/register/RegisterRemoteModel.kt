package ru.mikopizza.features.register
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val fullName: String,
    val password: String,
    val email: String,
    val phoneNumber: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)
