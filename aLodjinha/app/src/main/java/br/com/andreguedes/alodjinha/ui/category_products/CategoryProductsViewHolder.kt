package br.com.andreguedes.alodjinha.ui.category_products

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class CategoryProductsViewHolder<T> : RecyclerView.ViewHolder, View.OnClickListener {

    var item: T? = null
    private var rootView: View
    private var listener: OnItemClickListener?

    constructor(itemView: View) : this(itemView, null)

    constructor(itemView: View, listener: OnItemClickListener?) : super(itemView) {
        this.rootView = itemView
        this.listener = listener
    }

    protected fun onBindViewHolder(item: T) {
        this.item = item
        rootView.setOnClickListener(this)
    }

    abstract fun onCategoryProductsBindViewHolder(item: T)

    override fun onClick(view: View) {
        listener?.onItemClick(view, item)
    }

}