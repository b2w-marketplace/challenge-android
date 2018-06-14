package com.example.lidjinha.lodjinha.ui.home

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.model.Categorie
import com.example.lidjinha.lodjinha.ui.productscategory.ProductsCategoryActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val categories: List<Categorie>,
                        private val context: Context) :
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categories_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(context).load(categories[position].urlImage).into(holder.image, callbackImage(holder.image))
        holder.description.text = categories[position].description
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductsCategoryActivity::class.java)
            intent.putExtra(ProductsCategoryActivity.EXTRA_CATEGORIE, categories[position])
            context.startActivity(intent)
        }
    }

    private fun callbackImage(image: ImageView) = object : Callback {
        override fun onSuccess() {
            Log.d("Load categories", "Success!")
        }

        override fun onError() {
            image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_down_black))
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.iv_categorie_image)
        val description: TextView = itemView.findViewById(R.id.tv_categorie_description)

    }
}