package com.example.b2w.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.b2w.R
import com.example.b2w.model.Banner
import kotlinx.android.synthetic.main.adapter_banner.view.*

class BannerAdapter(private val context: Context, private val banners: MutableList<Banner>) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_banner, parent, false))
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(banners[position].urlImagem).into(holder.imgCategory)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgCategory: ImageView = view.img_category
    }
}