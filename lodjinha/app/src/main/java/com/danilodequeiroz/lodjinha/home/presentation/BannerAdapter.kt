package com.danilodequeiroz.lodjinha.home.presentation

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.home.domain.BannerViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banner.view.*
import java.lang.Exception
import com.danilodequeiroz.lodjinha.common.util.openUrl


class BannerAdapter(private val banners: MutableList<BannerViewModel>) : RecyclerView.Adapter<BannerViewHolder>() {


    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) = holder.bind(banners[position])

    override fun getItemCount(): Int = banners.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder =
        BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))

}


class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val resources = lazy { itemView.resources }

    fun bind(item: BannerViewModel) {
        loadImage(item)
    }

    fun loadImage(item: BannerViewModel){
        itemView.setOnClickListener(null)
        itemView.apply {
            progressBar.visibility = View.VISIBLE
            Picasso.get().load(item.urlImagem).error(R.drawable.ic_broken_image).into(imgBanner, object : Callback {
                override fun onSuccess() {
                    itemView.setOnClickListener{
                        val urlSubmarino = resources.getString(R.string.submarino_url)
                        it.context.openUrl(urlSubmarino)
                    }
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                    imgBanner.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.background_image_error))
                    itemView.setOnClickListener{loadImage(item)}
                }
            })
        }
    }

}