package ru.mikopizza.features.register
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val password: String,
    val email: String,
    val phone_number: String
)
@Serializable
data class RegisterResponseRemote(
    val token: String
)
