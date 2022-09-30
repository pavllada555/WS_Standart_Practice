package com.example.mikopizza.network.models.menuorders

import kotlinx.serialization.Serializable

@Serializable
data class MenuRequestModel(
    val searchQuery: String
)