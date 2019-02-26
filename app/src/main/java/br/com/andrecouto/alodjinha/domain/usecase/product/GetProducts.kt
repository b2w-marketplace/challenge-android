package br.com.andrecouto.alodjinha.domain.usecase.product

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProducts @Inject constructor(
        errorUtil: DomainErrorUtil,
        private val productRepository: ProductRepository) : SingleUseCase<List<Product>, GetProducts.Params>(errorUtil) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Product>> {
        if (params == null) throw IllegalArgumentException("params cannot be null")
        return productRepository.getProducts(params.categoryId)
    }

    data class Params constructor(val categoryId : Int){
        companion object {
            fun forProject(categoryId: Int) : Params {
                return Params(categoryId)
            }
        }
    }

}