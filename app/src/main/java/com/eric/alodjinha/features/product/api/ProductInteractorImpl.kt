package com.eric.alodjinha.features.product.api

import com.eric.alodjinha.features.product.model.Product
import io.reactivex.Observable

class ProductInteractorImpl : ProductInteractor {

    val repository = ProductRepository()

    companion object {

        val instance: ProductInteractor = ProductInteractorImpl()
    }

    override fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int): Observable<ProductResponse> {

        return repository.getProductsByCategory(offset, limite, categoriaId).map { it }
    }

    override fun productReservation(productId: Int): Observable<ProductReservationResponse> {

        return repository.productReservation(productId).map { it }
    }

    override fun getProductDetail(productId: Int): Observable<Product> {
      return repository.productDetail(productId).map { it }
    }
}