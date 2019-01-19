package br.com.cemobile.lodjinha.ui.home.adapters

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.cemobile.domain.model.Category
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.databinding.ListItemCategoryBinding
import br.com.cemobile.lodjinha.ui.home.CategoryViewModel

class CategoriesAdapter(
    private val itemClickListener: ((Category) -> Unit)?
) : ListAdapter<Category, CategoriesAdapter.ViewHolder>(CategoriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_category, parent, false
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position)?.let { category ->
            with(viewHolder) {
                itemView.tag = category
                bind(category, itemClickListener)
            }
        }
    }

    inner class ViewHolder(
        private val binding: ListItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, itemClickListener: ((Category) -> Unit)?) {
            with(binding) {
                viewModel = CategoryViewModel(category)
                rootLayout.setOnClickListener { itemClickListener?.invoke(category) }
                executePendingBindings()
            }
        }

    }

}