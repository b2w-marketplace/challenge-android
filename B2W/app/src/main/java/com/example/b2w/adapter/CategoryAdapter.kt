package com.example.b2w.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.b2w.R
import com.example.b2w.fragment.CategoryFragment
import com.example.b2w.model.Category
import kotlinx.android.synthetic.main.adapter_category.view.*

class CategoryAdapter(private val activity: FragmentActivity, private val categories: MutableList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(activity.baseContext).load(categories[position].urlImagem).into(holder.imgProduct)
        holder.txtCategory.text = categories[position].descricao

        holder.itemView.setOnClickListener {
            (activity.supportFragmentManager.fragments[1] as CategoryFragment).itemClicked(position)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imgProduct: ImageView = view.img_product
        var txtCategory: TextView = view.txt_category
    }
}