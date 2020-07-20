package com.howa.mvvmsampleapp.ui.home.products

import com.howa.mvvmsampleapp.R
import com.howa.mvvmsampleapp.data.db.entities.Products
import com.howa.mvvmsampleapp.databinding.ItemProductBinding
import com.xwray.groupie.databinding.BindableItem


class ProductItem(private val products: Products) : BindableItem<ItemProductBinding>() {

    override fun getLayout() = R.layout.item_product

    override fun bind(viewBinding: ItemProductBinding, position: Int) {
        viewBinding.product = products
    }
}
