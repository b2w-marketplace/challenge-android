package br.com.b2w.desafio.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

internal class BannerPageAdapter : PagerAdapter() {

    private var viewList: MutableList<View>? = null

    var data: List<View>?
        get() {
            if (viewList == null) {
                viewList = ArrayList()
            }

            return viewList
        }
        set(list) {
            this.viewList!!.clear()
            if (list != null && !list.isEmpty()) {
                this.viewList!!.addAll(list)
            }

            notifyDataSetChanged()
        }

    init {
        this.viewList = ArrayList()
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val view = viewList!![position]
        collection.addView(view)
        return view
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return viewList!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}