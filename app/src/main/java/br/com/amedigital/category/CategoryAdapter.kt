package br.com.amedigital.category

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.amedigital.R
import br.com.amedigital.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter (private var categories: List<Category>?,
                       private val listener: (Int) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories?.get(position)
        holder.bind(category, position, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val constraintLayoutContent: ConstraintLayout = itemView.constraintLayoutContent
        private val imageViewCategory : ImageView = itemView.imageviewCategory
        private val textViewDescriptionProd : TextView = itemView.textViewDescrProd

        fun bind(category: Category?, position: Int, listener: (Int) -> Unit) {
            Log.i(javaClass.simpleName, category?.urlImage)
            category?.apply {
                Picasso.get()
                    .load(urlImage)
                    .placeholder(R.mipmap.image_place_holder)
                    .into(imageViewCategory)
                textViewDescriptionProd.text = description
            }

            constraintLayoutContent.setOnClickListener {
                listener(position)
            }
        }
    }
}