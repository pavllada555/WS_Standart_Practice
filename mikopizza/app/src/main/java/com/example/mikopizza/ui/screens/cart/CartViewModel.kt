package com.example.mikopizza.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mikopizza.common.Cart
import com.example.mikopizza.common.EventHandler
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.ui.screens.cart.models.CartEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel(), EventHandler<CartEvent> {

    override fun obtainEvent(event: CartEvent) {
        when (event) {
            is CartEvent.ItemAddClicked -> addItem(event.value)
            is CartEvent.DecreaseItemCount -> removeItem(event.item)
            is CartEvent.IncreaseItemCount -> addItem(event.item)
            is CartEvent.MakeOrderClicked -> makeOrder()
        }
    }

    val apiService by lazy {
        ApiService.create()
    }

    private fun addItem(menuItem: MenuResponseModel) {
        Cart.addItem(menuItem)
    }

    private fun removeItem(menuItem: MenuResponseModel) {
        Cart.removeItem(menuItem)
    }

    private fun makeOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            val orderResult = apiService.tryMakeOrder(
                itemMap = Cart.cartItems.value!!
            )
        }
    }

}