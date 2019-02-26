package br.com.andrecouto.alodjinha.domain.usecase.product

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProductDetails @Inject constructor(
        errorUtil: DomainErrorUtil,
        private val productRepository: ProductRepository) : SingleUseCase<Product, GetProductDetails.Params>(errorUtil) {

    override fun buildUseCaseObservable(params: Params?): Single<Product> {
        if (params == null) throw IllegalArgumentException("params cannot be null")
        return productRepository.getProduct(params.productId)
    }

    data class Params constructor(val productId : Int){
        companion object {
            fun forProject(productId: Int) : Params {
                return Params(productId)
            }
        }
    }

}