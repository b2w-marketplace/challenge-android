package com.danilodequeiroz.lodjinha.productlist.domain

import com.danilodequeiroz.lodjinha.common.util.toBRLMoneyString
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.webapi.LodjinhaRestRepository
import com.danilodequeiroz.webapi.model.product.ProductsPayload
import io.reactivex.Single

const val PAGINATION_INTERVAL = 10

class ProductsListListInteractor(private val lodjinhaRestRepository: LodjinhaRestRepository) : ProductsListUseCase {
    private var lastOffSetValue = 0

    override fun getLastOffset(): Int {
        return lastOffSetValue
    }

    override fun getProducts(categoryId: Int, offset: Int, limit: Int): Single<MutableList<ProductViewModel>> {
        return lodjinhaRestRepository.getProducts(categoryId, offset, limit).map { payload ->
            lastOffSetValue = payload.offset
            payload.productsMapper()
        }
    }


}


private fun ProductsPayload.productsMapper(): MutableList<ProductViewModel> {
    return this.data?.map { product ->
        ProductViewModel(
            product.id,
            product.categoria?.id,
            product.nome,
            product.precoDe?.toBRLMoneyString(),
            product.precoPor?.toBRLMoneyString(),
            product.urlImagem
        )
    }?.toMutableList() ?: mutableListOf()
}
