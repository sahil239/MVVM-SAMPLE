package com.howa.mvvmsampleapp.ui.home.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.howa.mvvmsampleapp.data.repositories.ProductsRepository

@Suppress("UNCHECKED_CAST")
class ProductsModelFactory(private val productsRepository: ProductsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepository) as T
    }
}