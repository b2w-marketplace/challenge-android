package marcus.com.br.b2wtest.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banner.view.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.model.data.BannerData
import marcus.com.br.b2wtest.ui.BaseRecyclerAdapter

class BannersAdapter : BaseRecyclerAdapter<BannerData>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(bannerData: BannerData) = with(itemView) {
            Picasso.get()
                .load(bannerData.urlImage)
                .placeholder(R.drawable.progress_animation)
                .into(itemBannerImage)
        }
    }
}