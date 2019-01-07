package com.b2w.lodjinha.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var startPageNumber = 0
    var currentPage = 0

    private var scrollPosition = 0

    private var linearLayoutManager: LinearLayoutManager? = null

    private var spanArray: IntArray? = null

    init {
        this.linearLayoutManager = layoutManager as LinearLayoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount

        // End has been reached

        // Do something
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
            // End has been reached

            // Do something
            currentPage++

            onLoadMore(currentPage, totalItemCount)

            loading = true
        }
    }

    fun reset() {
        currentPage = startPageNumber
        this.previousTotal = 0
        this.loading = true
    }

    fun setStartPageNumber(startPageNumber: Int, restart: Boolean) {
        this.startPageNumber = startPageNumber
        if (restart)
            reset()
    }

    abstract fun onLoadMore(currentPage: Int, totalItemCount: Int)
    abstract fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int)

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }
}