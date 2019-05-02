package br.com.amedigital.challenge_android.view.viewholder

import android.view.View
import br.com.amedigital.challenge_android.models.entity.Banner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_banner_pager_list_item.view.*

class BannerListViewHolder (view: View, private val delegate: Delegate)
    : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(banner: Banner)
    }

    private lateinit var banner: Banner

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Banner) {
            banner = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            banner.urlImagem?.let{
                Glide
                    .with(context)
                    .load(banner.urlImagem)
                    .apply(RequestOptions())
                    .into(bannerImage)

            }

        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(banner)
    }

    override fun onLongClick(v: View?) = false
}