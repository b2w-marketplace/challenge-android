package br.com.andreguedes.alodjinha.ui.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.category_products.OnItemClickListener

class ProductsAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<ProductsViewHolder>() {

    private var products = arrayListOf<Product>()

    fun setProducts(productsList: List<Product>) {
        this.products.clear()
        this.products.addAll(productsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view, listener)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.onCategoryProductsBindViewHolder(products[position])
    }

}