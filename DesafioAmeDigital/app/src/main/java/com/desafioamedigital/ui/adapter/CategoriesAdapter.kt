package com.desafioamedigital.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.Category
import com.desafioamedigital.ui.activity.home.HomeActivity
import com.desafioamedigital.util.loadImage
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(private val context: HomeActivity, private val categoryList: List<Category>)
    : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(categoryList[position])

    override fun getItemCount(): Int = categoryList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        fun bind(item: Category){
            with(itemView){
                tv_category_description.text = item.description
                iv_category_item.loadImage(item.imageUrl, 250)
            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            context.onItemClick(adapterPosition, HomeActivity.RecyclerViewCode.CATEGORIES)
        }
    }

}