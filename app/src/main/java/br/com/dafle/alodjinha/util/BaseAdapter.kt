package br.com.dafle.alodjinha.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T>(@LayoutRes val view: Int,
                          val onBind:(T, View) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    var listItems: List<T> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(view, parent, false))
    }

    override fun getItemCount() =  listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(listItems[position], holder.itemView)
    }

    fun setItems(list: List<T>) {
        listItems = list
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)