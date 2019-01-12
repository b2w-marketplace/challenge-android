package br.com.android.seiji.mobileui.ui.productsByCategory.adapter

import android.graphics.Paint
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.utils.toCurrency
import com.squareup.picasso.Picasso

class ProductsByCategoryListAdapter(
    private var productsByCategoryList: MutableList<Product>,
    private val onItemClickListener: (product: Product, view: View) -> Unit
) : RecyclerView.Adapter<ProductsByCategoryListAdapter.ProductsByCategoryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsByCategoryListViewHolder {
        return ProductsByCategoryListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_by_category, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsByCategoryListViewHolder, position: Int) {
        holder.bind(productsByCategoryList[position]) { view ->
            onItemClickListener(productsByCategoryList[position], view)
        }
    }

    override fun getItemCount(): Int = productsByCategoryList.size

    fun addProducts(productList: List<Product>) {
        productsByCategoryList.addAll(productList)
    }

    open class ProductsByCategoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layoutItemProductByCategoryLayout: ConstraintLayout =
            view.findViewById(R.id.layoutItemProductByCategoryLayout)
        val imageProductByCategory: ImageView = view.findViewById(R.id.imageProductByCategory)
        val textProductByCategoryName: TextView = view.findViewById(R.id.textProductByCategoryName)
        val textProductByCategoryPreviousPrice: TextView =
            view.findViewById(R.id.textProductByCategoryPreviousPrice)
        val textProductByCategoryCurrentPrice: TextView =
            view.findViewById(R.id.textProductByCategoryCurrentPrice)

        open fun bind(product: Product, listener: (View) -> Unit) {
            Picasso.get()
                .load(product.urlImagem)
                .into(imageProductByCategory)

            textProductByCategoryName.text = product.descricao
            textProductByCategoryPreviousPrice.text = textProductByCategoryPreviousPrice.resources.getString(
                R.string.product_previous_price, product.precoDe.toCurrency()
            )

            textProductByCategoryPreviousPrice.paintFlags = textProductByCategoryPreviousPrice.paintFlags or
                    Paint.STRIKE_THRU_TEXT_FLAG
            textProductByCategoryCurrentPrice.text = product.precoPor.toCurrency()
            layoutItemProductByCategoryLayout.setOnClickListener { listener(layoutItemProductByCategoryLayout) }
        }
    }
}