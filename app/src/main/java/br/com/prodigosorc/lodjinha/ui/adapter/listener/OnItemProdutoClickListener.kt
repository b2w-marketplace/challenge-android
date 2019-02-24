package br.com.prodigosorc.lodjinha.ui.adapter.listener

import br.com.prodigosorc.lodjinha.models.Produto

interface OnItemProdutoClickListener {
    fun onItemClick(produto: Produto?)
}