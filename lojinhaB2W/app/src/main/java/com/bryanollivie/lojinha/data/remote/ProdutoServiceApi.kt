package com.bryanollivie.lojinha.data.remote

import com.bryanollivie.lojinha.data.model.ReturnBase

interface ProdutoServiceApi {

    interface ServiceCallback<T> {

        fun onLoaded(return_service: T)
    }

    fun produtoFindByCategoria(categoriaId: Int, offset: Int, limit: Int, callback: ServiceCallback<ReturnBase>)

    fun produtoFindById(produtoId: Int, callback: ServiceCallback<ReturnBase>)
    fun produtoMaisVendidos(callback: ServiceCallback<ReturnBase>)

    fun reservarProdutoById(produtoId: Int, callback: ServiceCallback<ReturnBase>)
}

