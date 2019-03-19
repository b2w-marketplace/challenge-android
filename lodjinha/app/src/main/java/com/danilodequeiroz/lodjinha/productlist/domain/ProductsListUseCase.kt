package com.danilodequeiroz.lodjinha.productlist.domain

import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import io.reactivex.Single

interface ProductsListUseCase {
    fun getProducts(categoryId:Int, offset: Int, limit:Int):  Single<MutableList<ProductViewModel>>
    fun getLastOffset():  Int
}
