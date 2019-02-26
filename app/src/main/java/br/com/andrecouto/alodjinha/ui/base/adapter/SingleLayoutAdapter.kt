package br.com.andrecouto.alodjinha.ui.base.adapter

import android.databinding.ViewDataBinding
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel

/**
 * Simplest implementation of [BaseAdapter] to use as a single layout adapter.
 */
open class SingleLayoutAdapter<T, B : ViewDataBinding>(
        private val layoutId: Int,
        items: List<T>,
        viewModel: BaseViewModel? = null,
        onBind: B.(Int) -> Unit = {}
) : BaseAdapter<T, B>(viewModel = viewModel, items = items, onBind = onBind) {

    override fun getLayoutId(position: Int): Int = layoutId
}