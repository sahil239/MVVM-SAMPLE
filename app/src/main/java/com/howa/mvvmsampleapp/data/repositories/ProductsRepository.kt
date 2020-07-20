package com.howa.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.howa.mvvmsampleapp.data.db.AppDatabase
import com.howa.mvvmsampleapp.data.db.entities.Products
import com.howa.mvvmsampleapp.data.network.APIService
import com.howa.mvvmsampleapp.data.network.SafeAPIRequest
import com.howa.mvvmsampleapp.data.preferences.PreferenceProvider
import com.howa.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository(
    private val apiService: APIService,
    private val appDatabase: AppDatabase,
    private val preferenceProvider: PreferenceProvider
) : SafeAPIRequest() {

    private val products = MutableLiveData<List<Products>>()

    init {
        products.observeForever {
            saveProducts(it)
        }
    }

    suspend fun getProducts(): LiveData<List<Products>> {
        return withContext(Dispatchers.IO) {
            fetchProducts()
            appDatabase.getProductDao().getProducts()
        }
    }

    private suspend fun fetchProducts() {
        if (isFetchNeeded()) {
            val response = apiRequest { apiService.getProducts() }
            products.postValue(response.data?.products)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveProducts(products: List<Products>) {

        Coroutines.io {
            appDatabase.getProductDao().saveAllProducts(products)
        }


    }
}