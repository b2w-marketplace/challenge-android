package com.caio.lodjinha.repository.remote.product

import javax.inject.Inject

class ProductRemoteRep @Inject constructor(private val productREST: ProductREST) {

    fun getProductsMoreSallers() = productREST.getProductsMoreSallers()

    fun getProductsByCategory(offset: Int, limite: Int,categoriaId: Int) = productREST.getProductsByCategory(offset,limite,categoriaId)

    fun productReservation(produtoId: Int) = productREST.productReservation(produtoId)

    fun getProductDetail(produtoId: Int) = productREST.getProductDetail(produtoId)
}