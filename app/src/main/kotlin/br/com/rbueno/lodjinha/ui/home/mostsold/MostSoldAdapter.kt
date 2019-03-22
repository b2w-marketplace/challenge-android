package br.com.rbueno.lodjinha.ui.home.mostsold

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.MostSold
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.util.ImageLoader
import br.com.rbueno.lodjinha.util.toMoneyDisplay

class MostSoldAdapter(private val items: MostSold) : RecyclerView.Adapter<MostSoldViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostSoldViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_product_list_item, parent, false)
        return MostSoldViewHolder(view)
    }

    override fun getItemCount() = items.data.size

    override fun onBindViewHolder(holder: MostSoldViewHolder, position: Int) {
        holder.bindView(items.data[position])
    }

}

class MostSoldViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imageProduct by lazy { view.findViewById<ImageView>(R.id.image_product) }
    private val textProductName by lazy { view.findViewById<TextView>(R.id.text_product_name) }
    private val textOldPrice by lazy { view.findViewById<TextView>(R.id.text_old_price) }
    private val textNewPrice by lazy { view.findViewById<TextView>(R.id.text_new_price) }

    fun bindView(product: Product) {
        with(product) {
            ImageLoader.loadImage(urlImage, imageProduct)
            textProductName.text = name
            textOldPrice.text =
                String.format(view.context.getString(R.string.old_price_format), oldPrice.toMoneyDisplay())
            textNewPrice.text =
                String.format(view.context.getString(R.string.new_price_format), newPrice.toMoneyDisplay())
        }
    }
}