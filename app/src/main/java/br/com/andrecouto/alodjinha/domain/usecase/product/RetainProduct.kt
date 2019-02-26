package br.com.andrecouto.alodjinha.domain.usecase.product

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RetainProduct @Inject constructor(
        errorUtil: DomainErrorUtil,
        val productRepository: ProductRepository
): CompletableUseCase<RetainProduct.Params>(errorUtil){

    override fun buildUseCaseObservable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("params cannot be null")
        return productRepository.retainProduct(params.productId)
    }

    data class Params constructor(var productId: Int) {
        companion object {
            fun forProject(productId: Int) : Params{
                return Params(productId)
            }
        }
    }
}