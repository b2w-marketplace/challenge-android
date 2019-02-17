package br.com.b2w.lodjinha.features.product

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.b2w.lodjinha.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_best_seller_products.view.*
import kotlinx.android.synthetic.main.product_item.view.*

class BestSellerProductsView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val products: MutableList<Product> = mutableListOf()

    init {
        View.inflate(context, R.layout.view_best_seller_products, this)
    }

    fun setProducts(products: List<Product>) {
        this.products.addAll(products)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(bestSellerProductsRecyclerView) {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = BestSellerProductsAdapter()
        }
    }

    inner class BestSellerProductsAdapter : RecyclerView.Adapter<BestSellerProductsAdapter.BestSellerProductsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerProductsViewHolder =
            BestSellerProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.product_item, parent, false))

        override fun getItemCount(): Int = products.size

        override fun onBindViewHolder(holder: BestSellerProductsViewHolder, position: Int) {
            val product = products[position]
            holder.bind(product)
        }

        inner class BestSellerProductsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

            fun bind(product: Product) {
                with(view) {
                    Picasso.get()
                        .load(product.urlImagem)
//                        .placeholder(R.drawable.ic_exclamation_circle_solid)
                        .into(productImageView)
                    productNameTextView.text = product.name
                    productOldPriceTextView.text = context.getString(R.string.product_old_price, product.oldPrice.toString())
                    productNewPriceTextView.text = context.getString(R.string.product_new_price, product.newPrice.toString())
                }
            }

        }
    }
}