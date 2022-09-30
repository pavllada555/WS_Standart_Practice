package com.example.mikopizza.ui.screens.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mikopizza.common.EventHandler
import com.example.mikopizza.common.MenuRepository
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.app.Application
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.ui.screens.home.models.HomeEvent
import com.example.mikopizza.ui.screens.home.models.HomeSubState
import com.example.mikopizza.ui.screens.home.models.HomeViewState
import com.example.mikopizza.ui.screens.home.models.ItemClickAction
import io.ktor.utils.io.errors.*
import okhttp3.internal.notify

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), EventHandler<HomeEvent> {
    private val _viewState = MutableLiveData(HomeViewState())
    val viewState: LiveData<HomeViewState> = _viewState

    private val repository: MenuRepository
    var menuItems: MutableLiveData<List<MenuResponseModel>> = MutableLiveData<List<MenuResponseModel>>(
    mutableListOf<MenuResponseModel>())

    private val apiService by lazy {
        ApiService.create()
    }
    init {
        repository = MenuRepository(application)
        refreshMenuFromRepository()
    }

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ItemActionInvoked -> itemActionInvoked()
            HomeEvent.SearchClearClicked -> clearSearch()
            HomeEvent.RetryMenuLoadingClicked -> refreshMenuFromRepository()
            is HomeEvent.SearchChanged -> searchChanged(event.value)
            is HomeEvent.MenuItemClicked -> menuItemClicked(event.value)
        }
    }

    private fun itemActionInvoked() {
        _viewState.postValue(_viewState.value?.copy(itemClickAction = ItemClickAction.None))
    }

    private fun clearSearch() {
        _viewState.postValue(_viewState.value?.copy(searchValue = ""))
    }

    private fun searchChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(searchValue = value))
    }

    private fun menuItemClicked(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(
                itemClickAction = ItemClickAction.OpenItemPicker(
                    value
                )
            )
        )
    }

    private fun refreshMenuFromRepository() {
        viewModelScope.launch {
            try {
                println("first")
                _viewState.postValue(_viewState.value?.copy(homeSubState = HomeSubState.Loading))
                MenuRepository.fetchMenu()
                println("second")
//                menuItems.postValue(
//                    apiService.getProducts("")
//                )
                println("third")
                _viewState.postValue(_viewState.value?.copy(homeSubState = HomeSubState.Loaded))
                println("4th")

            } catch (networkError: IOException) {
                if (MenuRepository.menuItems.value?.isNotEmpty() == true) {
                    _viewState.postValue(_viewState.value?.copy(homeSubState = HomeSubState.Loaded))
                }
                _viewState.postValue(_viewState.value?.copy(homeSubState = HomeSubState.Failed))
            }
        }
    }
}