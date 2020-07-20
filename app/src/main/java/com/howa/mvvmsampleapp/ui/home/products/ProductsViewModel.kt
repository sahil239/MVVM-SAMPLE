package com.howa.mvvmsampleapp.ui.home.products

import androidx.lifecycle.ViewModel
import com.howa.mvvmsampleapp.data.repositories.ProductsRepository
import com.howa.mvvmsampleapp.util.lazyDeferred

class ProductsViewModel(repository: ProductsRepository) : ViewModel() {

    val products by lazyDeferred { repository.getProducts() }
}