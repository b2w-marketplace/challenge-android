package br.com.cemobile.lodjinha.ui.home.adapters

import android.support.v7.util.DiffUtil
import br.com.cemobile.domain.model.Category

class CategoriesDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.description == newItem.description &&
        oldItem.imageUrl == newItem.imageUrl

}