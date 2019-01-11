package br.com.android.seiji.mobileui.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var currentPage = 0
    private var scrollPosition = 0
    private var linearLayoutManager: LinearLayoutManager? = null

    init {
        this.linearLayoutManager = layoutManager as LinearLayoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount

        when {
            linearLayoutManager != null -> {
                totalItemCount = linearLayoutManager!!.itemCount
                firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
            }
        }

        scrollPosition = recyclerView.computeVerticalScrollOffset()
        onScroll(firstVisibleItem, dy, scrollPosition)

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            currentPage++
            onLoadMore(currentPage, totalItemCount)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int, totalItemCount: Int)
    abstract fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int)
}