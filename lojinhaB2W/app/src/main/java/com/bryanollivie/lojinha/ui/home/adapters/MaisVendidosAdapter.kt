package com.bryanollivie.lojinha.ui.home.adapters

import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_produto.view.*

class MaisVendidosAdapter(
    val items: List<Any>,
    val itemClick: (Any) -> Unit
) : RecyclerView.Adapter<MaisVendidosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_produto, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemModel: Any) {

            Picasso.get()
                .load(Produto.toObject(itemModel)["urlImagem"].toString())
                .centerInside()
                .fit()
                .into(itemView.rowMaisVendidosImage)
            val precoDe = Produto.toObject(itemModel)["precoDe"].toString().replace(".", ",")

            itemView.rowMaisVendidosTitle.text = Produto.toObject(itemModel)["nome"].toString()
            //itemView.rowMaisVendidosPor.text = Produto.toObject(itemModel)["precoPor"].toString().replace(".", ",")


            itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

