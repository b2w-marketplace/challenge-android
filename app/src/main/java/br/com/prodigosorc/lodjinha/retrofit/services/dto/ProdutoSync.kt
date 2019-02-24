package br.com.prodigosorc.lodjinha.retrofit.services.dto

import br.com.prodigosorc.lodjinha.models.Produto

class ProdutoSync {
    private val data: List<Produto> = mutableListOf()
    private val offset: Int = 0
    private val total: Int = 0

    fun getData(): List<Produto> = data
    fun getOffset() : Int = offset
    fun getTotal() : Int = total
}