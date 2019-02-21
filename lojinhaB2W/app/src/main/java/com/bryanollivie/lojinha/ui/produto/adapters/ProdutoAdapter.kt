package com.bryanollivie.lojinha.ui.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_produto.view.*

class ProdutoAdapter(
    val items: List<Any>,
    val itemClick: (String) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_produto, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemModel: Any) {

            Picasso.get()
                .load(Produto.toObject(itemModel)["urlImagem"].toString())
                .centerCrop()
                .fit()
                .into(itemView.rowMaisVendidosImage)
            itemView.rowMaisVendidosTitle.text = Produto.toObject(itemModel)["nome"].toString()
            itemView.rowMaisVendidosDe.text = Produto.toObject(itemModel)["precoDe"].toString().replace(".", ",")
            itemView.rowMaisVendidosPor.text = Produto.toObject(itemModel)["nome"].toString().replace(".", ",")

            itemView.setOnClickListener {
                itemClick(Produto.toObject(itemModel)["id"].toString())
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

