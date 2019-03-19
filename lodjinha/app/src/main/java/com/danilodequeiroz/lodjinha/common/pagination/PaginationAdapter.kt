package com.danilodequeiroz.lodjinha.common.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danilodequeiroz.lodjinha.R


private const val LOADING_VIEW_TYPE = 0
private const val ITEM_VIEW_TYPE = 1

abstract class PaginationAdapter<D> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    abstract fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
    abstract fun addLoadingViewFooter()
    
    private var isLoadingViewAdded = false
    protected var dataList = mutableListOf<D>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LOADING_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_loading_footer, parent, false)
                LoadingViewHolder(view)
            }
            else -> onCreateItemViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) != LOADING_VIEW_TYPE) onBindItemViewHolder(holder, position)
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int) =
            if (position == dataList.size -1 && isLoadingViewAdded) LOADING_VIEW_TYPE else ITEM_VIEW_TYPE

    fun removeLoadingViewFooter() {
        if (isLoadingViewAdded && dataList.size > 0) {
            isLoadingViewAdded = false
            dataList.removeAt(dataList.size-1)
            notifyItemRemoved(dataList.size)
        }
    }

    protected fun addLoadingViewFooter(emptyDataObject: D) {
        if (dataList.size > 0) {
            isLoadingViewAdded = true
            dataList.add(emptyDataObject)
            notifyItemInserted(dataList.size - 1)
        }
    }

}

class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)