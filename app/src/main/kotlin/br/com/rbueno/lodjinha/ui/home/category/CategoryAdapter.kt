package br.com.rbueno.lodjinha.ui.home.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.CategoryItem
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.util.ImageLoader

class CategoryAdapter(private val items: Category, private val clickListener: (category: CategoryItem) -> Unit) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_category_list_item, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun getItemCount() = items.categoryItem.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(items.categoryItem[position])
    }

}

class CategoryViewHolder(private val view: View, private val clickListener: (category: CategoryItem) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val imageCategory by lazy { view.findViewById<ImageView>(R.id.image_category) }
    private val textCategoryName by lazy { view.findViewById<TextView>(R.id.text_category_name) }


    fun bindView(categoryItem: CategoryItem) {
        view.setOnClickListener { clickListener.invoke(categoryItem) }
        with(categoryItem) {
            ImageLoader.loadImage(urlImage, imageCategory)
            textCategoryName.text = description
        }
    }
}