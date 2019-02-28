package com.caio.lodjinha.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caio.challengeandroid.R
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.repository.entity.Category
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onClick: (Category) -> Unit = {}

    private var categories: List<Category>? = null

    fun setListCategories(category: List<Category>){
        this.categories = category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categories?.get(position)?.let {category ->
            holder.imageViewCategory.loadUrl(category.urlImagem,holder.process)

            holder.textViewCategory.text = category.descricao
            holder.itemView.setOnClickListener { onClick(category) }
        }
    }

    override fun getItemCount(): Int {
        return categories!!.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategory = view.tvCategory
        val imageViewCategory = view.ivCategory
        val process = view.progress

    }
}