package br.com.prodigosorc.lodjinha.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Categoria
import android.support.v7.widget.RecyclerView.Adapter
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemCategoriaClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categoria_item.view.*

class ListaCategoriaAdapter(
    private val categorias: List<Categoria>?,
    private val context: Context?,
    private var onItemProdutoClickListener: OnItemCategoriaClickListener? = null
) : Adapter<ListaCategoriaAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias?.get(position)
        holder.let {
            Picasso.get()
                .load(categoria?.urlImagem)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(it.imagemCategoria)
            it.descricao.text = categoria?.descricao
            it.itemView.setOnClickListener { onItemProdutoClickListener?.onItemClick(categoria?.descricao) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.categoria_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagemCategoria = itemView.categoria_item_iv_categoria
        val descricao = itemView.categoria_item_tv_descricao_categoria
    }

    override fun getItemCount(): Int {
        return categorias?.size!!
    }

    fun setOnItemClickListener(onItemProdutoClickListener: OnItemCategoriaClickListener) {
        this.onItemProdutoClickListener = onItemProdutoClickListener
    }
}