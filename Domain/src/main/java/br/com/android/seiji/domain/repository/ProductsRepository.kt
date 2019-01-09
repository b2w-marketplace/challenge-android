package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Product
import io.reactivex.Observable

interface ProductsRepository {
    fun getProducts(): Observable<List<Product>>
}