package com.example.mikopizza.common

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mikopizza.db.AppDatabase
import com.example.mikopizza.db.asDomainModel
import com.example.mikopizza.network.ApiService
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.network.models.menuorders.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MenuRepository {
    private val apiService by lazy {
        ApiService.create()
    }

    private lateinit var database: AppDatabase
    lateinit var menuItems: MutableLiveData<List<MenuResponseModel>>

    fun menuItemByName(name: String): MenuResponseModel {
        return menuItems.value!!.first {
            it.name == name
        }
    }

    suspend fun fetchMenu() {
        withContext(Dispatchers.IO) {
            val menu = apiService.getProducts("")
            menuItems.postValue(menu)

//            database.menuDao().insertAll(
//                menu.asDatabaseModel()
//            )
        }
    }

    operator fun invoke(context: Context): MenuRepository {
//        database = AppDatabase.getInstance(context)
//        menuItems = Transformations.map(database.menuDao().getMenu()) {
//            it.asDomainModel()
//        }
        menuItems = MutableLiveData(mutableListOf<MenuResponseModel>())
        return this
    }
}