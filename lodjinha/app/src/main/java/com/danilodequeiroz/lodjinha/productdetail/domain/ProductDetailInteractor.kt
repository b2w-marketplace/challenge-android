package com.danilodequeiroz.lodjinha.productdetail.domain

import com.danilodequeiroz.lodjinha.common.util.toBRLMoneyString
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.webapi.LodjinhaRestRepository
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import com.danilodequeiroz.webapi.model.product.ProductsPayload
import io.reactivex.Single


class ProductDetailInteractor(private val lodjinhaRestRepository: LodjinhaRestRepository) : ProductDetailUseCase {
    override fun getProduct(productId: Int): Single<DetailedProducViewModel> {
        return lodjinhaRestRepository.getProduct(productId).map { product ->
            DetailedProducViewModel(
                    product.id,
                    product.categoria?.id,
                    product.categoria?.descricao,
                    product.nome,
                    product.descricao,
                    product.precoDe?.toBRLMoneyString(),
                    product.precoPor?.toBRLMoneyString(),
                    product.urlImagem
            )
        }
    }

    override fun reserveProduct(productId: Int): Single<ReserveProductPayload> {
        return lodjinhaRestRepository.reserveProduct(productId)
    }
}
