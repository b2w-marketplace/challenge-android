package com.bryanollivie.lojinha.ui.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_mais_vendidos.view.*

class MaisVendidosAdapter(
    val items: List<Produto>,
    val itemClick: (Produto) -> Unit
) : RecyclerView.Adapter<MaisVendidosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_categoria, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemModel: Produto) {
            with(itemModel) {

                if (!itemModel.urlImagem.isNullOrBlank()) {
                    Picasso.get()
                        .load(itemModel.urlImagem)
                        .centerCrop()
                        .fit()
                        .into(itemView.rowMaisVendidosImage)
                }
                itemView.rowMaisVendidosTitle.text = itemModel.nome
                itemView.rowMaisVendidosDe.text = itemModel.precoDe.toString().replace(".", ",")
                itemView.rowMaisVendidosPor.text = itemModel.precoPor.toString().replace(".", ",")


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

