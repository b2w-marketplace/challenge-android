package br.com.android.seiji.mobileui.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.extensions.inflate
import br.com.android.seiji.mobileui.ui.ClickListener
import com.bumptech.glide.Glide
import javax.inject.Inject

class BannerAdapter @Inject constructor() : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    var banners: List<Banner> = arrayListOf()
    var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_banner))

    override fun getItemCount() = banners.count()

    override fun onBindViewHolder(holder: BannerAdapter.ViewHolder, position: Int) {
        val banner = banners[position]

        Glide.with(holder.bannerImage.context)
            .load(banner.urlImagem)
            .into(holder.bannerImage)

        holder.itemView.setOnClickListener {
            clickListener?.onBannerClicked(banner.linkUrl)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bannerImage: ImageView = view.findViewById(R.id.bannerImageView)
    }
}