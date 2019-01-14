package alodjinha.cfgdemelo.com.view.adapter

import alodjinha.cfgdemelo.com.model.Category
import alodjinha.cfgdemelo.com.view.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CategoriesAdapter(val context: Context, val categories: List<Category>, private val categoryClick: CategoryClickListener): RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoriesAdapter.RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_view_holder, parent, false)

        return RecyclerViewHolder(view, categoryClick)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.RecyclerViewHolder, position: Int) {
        holder.bindData(context, categories, position)
    }

    class RecyclerViewHolder(itemView: View, private val categoryClick: CategoryClickListener): RecyclerView.ViewHolder(itemView) {
        private val ivCategory: ImageView = itemView.findViewById(R.id.ivCategory)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

        fun bindData(context: Context, categories: List<Category>, position: Int) {
            Picasso.with(context)
                .load(categories[position].imageUrl)
                .into(ivCategory)
            tvCategory.text = categories[position].description
            itemView.setOnClickListener {
                categoryClick.getCategory(context, categories[position])
            }
        }
    }

    interface CategoryClickListener {
        fun getCategory(context: Context, category: Category)
    }

}