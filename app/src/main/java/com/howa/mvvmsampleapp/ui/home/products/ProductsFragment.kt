package com.howa.mvvmsampleapp.ui.home.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.howa.mvvmsampleapp.R
import com.howa.mvvmsampleapp.data.db.entities.Products
import com.howa.mvvmsampleapp.util.Coroutines
import com.howa.mvvmsampleapp.util.hide
import com.howa.mvvmsampleapp.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.products_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProductsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: ProductsModelFactory by instance()

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(ProductsViewModel::class.java)

        bindUI()
        Coroutines.main {
            val products = viewModel.products.await()
            products.observe(viewLifecycleOwner, Observer {
                tvSize.text = it.size.toString()
                //context?.toast(""+it.size)
            })
        }
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.products.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerView(it.getProductList())
        })
    }

    private fun initRecyclerView(products: List<ProductItem>) {

        val adapterGroup = GroupAdapter<GroupieViewHolder>().apply { addAll(products) }

        recyclerview.apply {
            setHasFixedSize(true)
            adapter = adapterGroup
        }
    }

    private fun List<Products>.getProductList(): List<ProductItem> {
        return this.map { ProductItem(it) }
    }

}