package ru.mikopizza.features.utils

import ru.mikopizza.database.tokens.Tokens

object TokenCheck{
    fun isTokenValid(token: String): Boolean = Tokens.fetchTokens().firstOrNull {it.token == token} != null
    fun isTokenAdmin(token: String): Boolean = true // Boolean = token == "Token"
}