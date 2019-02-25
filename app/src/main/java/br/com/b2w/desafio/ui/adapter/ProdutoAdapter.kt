package br.com.b2w.desafio.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.CardViewProdutoBinding
import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.util.Utils
import com.bumptech.glide.Glide
import java.text.NumberFormat

class ProdutoAdapter(val context: Context, val listener: AgendaAdapterOnClickListener) :
        RecyclerView.Adapter<ProdutoAdapter.MovieViewHolder>() {

    private var items: List<Produto?> = ArrayList()
    private var itemsFiltered: List<Produto?> = ArrayList()

    interface AgendaAdapterOnClickListener {
        fun onClickProduto(agenda: Produto?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewProdutoBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    fun getItemByPosition(position: Int): Produto {
        return items[position]!!
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    fun setData(produtos: List<Produto?>) {
        items = produtos
        itemsFiltered = produtos
        notifyDataSetChanged()
    }

    fun addData(produtos: List<Produto?>) {
        var nprodutos: MutableList<Produto?> = ArrayList()
        nprodutos.addAll(items)
        nprodutos.addAll(produtos)

        items = nprodutos
        itemsFiltered = nprodutos
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: CardViewProdutoBinding) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Produto?) {

            Glide
                .with(context)
                .load(item!!.urlImagem)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_nao_disponivel)
                .into(binding.ivProduto)

            val defaultFormat = NumberFormat.getInstance(Utils.getCurrentLocale(context))

            binding.tvDescricao.text = item.nome
            binding.tvPrecoDe.text = "De: " + defaultFormat.format(item.precoDe)
            binding.tvPrecoDe.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvPrecoPor.text = "Por " + defaultFormat.format(item.precoPor)
        }

        override fun onClick(v: View?) {
            listener.onClickProduto(items[adapterPosition])
        }
    }
}