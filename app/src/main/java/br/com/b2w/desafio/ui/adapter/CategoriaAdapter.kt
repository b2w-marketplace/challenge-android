package br.gov.sp.detran.consultas.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.CardViewCategoriaBinding
import br.com.b2w.desafio.model.Categoria
import com.bumptech.glide.Glide

class CategoriaAdapter(val context: Context, val listener: AgendaAdapterOnClickListener) :
        RecyclerView.Adapter<CategoriaAdapter.MovieViewHolder>() {

    private var items: List<Categoria?> = ArrayList()
    private var itemsFiltered: List<Categoria?> = ArrayList()

    interface AgendaAdapterOnClickListener {
        fun onClickCategoria(agenda: Categoria?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewCategoriaBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    fun setData(categorias: List<Categoria?>) {
        items = categorias
        itemsFiltered = categorias
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: CardViewCategoriaBinding) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Categoria?) {
            Glide
                .with(context)
                .load(item!!.urlImagem)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_nao_disponivel)
                .into(binding.ivProduto)

            binding.tvCategoria.text = item.descricao
        }

        override fun onClick(v: View?) {
            listener.onClickCategoria(items[adapterPosition])
        }
    }
}