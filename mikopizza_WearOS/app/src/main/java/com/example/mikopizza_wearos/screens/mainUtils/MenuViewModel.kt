package com.example.mikopizza_wearos.screens.mainUtils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.mikopizza_wearos.network.ApiService
import com.example.mikopizza_wearos.network.models.OrderFetchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val value: String,
)

@HiltViewModel
class MenuViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state = mutableStateOf(MainState(""))
        private set

    private val _orders: MutableLiveData<MutableList<OrderFetchModel>> = MutableLiveData(
        mutableListOf())
    val orders: LiveData<MutableList<OrderFetchModel>> = _orders

    private val apiService by lazy {
        ApiService.create()
    }

    private fun getOrderListModel() {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.tryGetOrders(state.value.value)?.orderList?.forEach {
                _orders.value?.add(it)
            }

        }
    }

    init{
        val argument = savedStateHandle.get<String>("email").orEmpty()
        state.value = state.value.copy(
            value = argument
        )
        getOrderListModel()
    }
}