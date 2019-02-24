package br.com.prodigosorc.lodjinha.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Produto
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemProdutoClickListener
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PREFIXO_DE
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PREFIXO_POR
import br.com.prodigosorc.lodjinha.util.extensions.limitarTitulos
import br.com.prodigosorc.lodjinha.util.extensions.formataParaBrasileiro
import br.com.prodigosorc.lodjinha.util.extensions.strike
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.produto_item.view.*

class ListaProdutoAdapter(
    private val produtos: List<Produto>?,
    private val context: Context?,
    private var onItemProdutoClickListener: OnItemProdutoClickListener? = null
) : RecyclerView.Adapter<ListaProdutoAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos?.get(position)
        holder.let {
            Picasso.get()
                .load(produto?.urlImagem)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(it.imagemProduto)
            if (produto != null) {
                it.descricao.text = limitarTitulos(produto.descricao, 80)
                it.precoDe.text = produto.precoDe.formataParaBrasileiro(PREFIXO_DE)
                it.precoDe.strike()
                it.precoPor.text = produto.precoPor.formataParaBrasileiro(PREFIXO_POR)
                it.itemView.setOnClickListener { onItemProdutoClickListener?.onItemClick(produto) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagemProduto = itemView.iv_produto
        val descricao = itemView.produto_item_tv_descricao
        val precoDe = itemView.produto_item_tv_preco_de
        val precoPor = itemView.produto_item_tv_preco_por
    }

    override fun getItemCount(): Int {
        return produtos?.size!!
    }

    fun setOnItemClickListener(onItemProdutoClickListener: OnItemProdutoClickListener) {
        this.onItemProdutoClickListener = onItemProdutoClickListener
    }
}