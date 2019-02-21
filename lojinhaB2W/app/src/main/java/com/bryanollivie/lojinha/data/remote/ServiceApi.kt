package com.bryanollivie.lojinha.data.remote

import com.bryanollivie.lojinha.data.model.ReturnBase

interface ServiceApi {

    interface ServiceCallback<T> {

        fun onLoaded(return_service: T)
    }

    fun categoriaFindAll(callback: ServiceCallback<ReturnBase>)

    fun produtoMaisVendidos(callback: ServiceCallback<ReturnBase>)

    fun produtoFindByCategoria(categoriaId: Int, callback: ServiceCallback<ReturnBase>)

    fun produtoFindById(produtoId: Int, callback: ServiceCallback<ReturnBase>)

    /*fun bannerFindAll(callback: ServiceCallback<ReturnBase>)



    fun reservarProdutoById(produtoId: Int, callback: ServiceCallback<ReturnBase>)*/
}

