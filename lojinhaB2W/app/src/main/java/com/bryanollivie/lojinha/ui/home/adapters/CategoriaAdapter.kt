package com.bryanollivie.lojinha.ui.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Categoria
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_categoria.view.*

class CategoriaAdapter(
    val items: List<Categoria>,
    val itemClick: (Categoria) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_categoria, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemModel: Categoria) {
            with(itemModel) {

                if (!itemModel.urlImagem.isNullOrBlank()) {
                    Picasso.get()
                        .load(itemModel.urlImagem)
                        .centerCrop()
                        .fit()
                        .into(itemView.rowCategoriaImage)
                }
                itemView.rowCategoriaDesc.text = itemModel.descricao
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

