package br.com.andrecouto.alodjinha.ui.fragment.home.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andrecouto.alodjinha.databinding.ItemViewBannerBinding
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.ui.fragment.home.HomeViewModel

class BannerPagerAdapter constructor(private val viewModel: HomeViewModel) : PagerAdapter() {

    val items = mutableListOf<Banner>()

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(collection.context)
        val binding = ItemViewBannerBinding.inflate(inflater, collection, false)
        binding.vm = viewModel
        binding.item = items[position]
        binding.root.tag = position

        collection.addView(binding.root)
        return binding.root
    }

    override fun getItemPosition(`object`: Any): Int {
        val index = items.indexOf((`object` as View).tag)
        return if (index == -1)
            PagerAdapter.POSITION_NONE
        else
            index
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) = collection.removeView(view as View)

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence? = items[position].id.toString()

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`
}