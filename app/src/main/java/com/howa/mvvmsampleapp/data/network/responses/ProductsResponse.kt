package com.howa.mvvmsampleapp.data.network.responses

import com.howa.mvvmsampleapp.data.db.entities.Products

data class ProductsResponse(
    val success: Boolean,
    val message: String?,
    val status_code: Int?,
    val data: com.howa.mvvmsampleapp.data.network.responses.Products?
)

class Products(val products: List<Products>)