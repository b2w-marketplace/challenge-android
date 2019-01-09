package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Product
import io.reactivex.Completable
import io.reactivex.Observable

interface ProductRepository {
    fun getBestSellerProducts(): Observable<List<Product>>
    fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Observable<List<Product>>
    fun doProductReservation(productId: Int): Completable
}