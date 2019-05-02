package br.com.amedigital.challenge_android.view.viewholder

import android.view.View
import br.com.amedigital.challenge_android.models.entity.Categoria
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_categoria_list_item.view.*

class CategoriaListViewHolder (view: View)
    : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(categoria: Categoria)
    }

    private lateinit var categoria: Categoria


    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Categoria) {
            categoria = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            categoria.urlImagem?.let{
                Glide
                    .with(context)
                    .load(categoria.urlImagem)
                    .apply(RequestOptions())
                    .into(categoriaImage)

            }

        }
    }

    override fun onClick(v: View?) {}

    override fun onLongClick(v: View?) = false
}