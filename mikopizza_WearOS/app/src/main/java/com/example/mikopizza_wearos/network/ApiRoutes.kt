package com.example.mikopizza_wearos.network

object ApiRoutes {
    private const val BASE_URL = "http://10.0.2.2:8080"
    const val MENU_SEARCH = "$BASE_URL/menu/search"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val FETCH_ORDERS = "$BASE_URL/order/fetch_orders"
}
