package com.example.mikopizza.navigation

import com.example.mikopizza.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Cart : NavigationItem("cart", R.drawable.ic_cart, "Cart")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "Profile")
    object Recent : NavigationItem("recent", R.drawable.ic_recent, "Recent")
}