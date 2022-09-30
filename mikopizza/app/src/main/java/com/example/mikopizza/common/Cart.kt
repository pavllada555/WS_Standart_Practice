package com.example.mikopizza.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mikopizza.network.models.menuorders.MenuResponseModel

data class OrderLine(
    val item: MenuResponseModel, var count: Int
)

object Cart {
    private val _cartItems: MutableLiveData<MutableMap<MenuResponseModel, Int>> =
        MutableLiveData(mutableMapOf())
    val cartItems: LiveData<MutableMap<MenuResponseModel, Int>> = _cartItems

    fun addItem(menuItem: MenuResponseModel) {
        if (_cartItems.value?.containsKey(menuItem) == false) {
            val newCartItems = _cartItems.value?.toMutableMap()
            newCartItems?.put(menuItem, 1)
            _cartItems.postValue(newCartItems)
        } else {
            _cartItems.postValue(_cartItems.value?.map { (key, value) ->
                if (key == menuItem) {
                    Pair(key, value + 1)
                } else {
                    Pair(key, value)
                }
            }?.toMap()?.toMutableMap())
        }
    }

    fun removeItem(menuItem: MenuResponseModel, removeCompletely: Boolean = false) {
        if (removeCompletely) {
            _cartItems.postValue(_cartItems.value?.filter {
                it.key != menuItem
            }?.toMutableMap())
        } else {
            if (_cartItems.value!!.get(menuItem) != 1) {
                _cartItems.postValue(_cartItems.value?.map { (key, value) ->
                    if (key == menuItem) {
                        Pair(key, value - 1)
                    } else {
                        Pair(key, value)
                    }
                }?.toMap()?.toMutableMap())
            } else {
                val newCartItems = _cartItems.value?.toMutableMap()
                newCartItems?.remove(menuItem)
                _cartItems.postValue(newCartItems)
            }
        }
    }


}