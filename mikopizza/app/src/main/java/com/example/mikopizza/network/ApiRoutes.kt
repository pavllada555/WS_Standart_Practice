package com.example.mikopizza.network

object ApiRoutes {
    private const val BASE_URL = "http://10.0.2.2:8080"
    const val MENU_SEARCH = "$BASE_URL/menu/search"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val MAKE_ORDER = "$BASE_URL/order/make_order"
}
