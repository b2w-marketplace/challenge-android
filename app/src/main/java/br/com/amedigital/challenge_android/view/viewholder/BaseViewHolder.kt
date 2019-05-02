package br.com.amedigital.challenge_android.view.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused", "LeakingThis")
abstract class BaseViewHolder(private val view: View)
    : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    /** binds data to the view holder class. */
    @Throws(Exception::class)
    abstract fun bindData(data: Any)

    /** gets the view of the [RecyclerView.ViewHolder]. */
    fun view(): View {
        return view
    }

    /** gets the context. */
    fun context(): Context {
        return view.context
    }
}