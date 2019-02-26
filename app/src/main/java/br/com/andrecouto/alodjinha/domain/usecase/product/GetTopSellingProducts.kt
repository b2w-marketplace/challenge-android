package br.com.andrecouto.alodjinha.domain.usecase.product

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetTopSellingProducts @Inject constructor(
        errorUtil: DomainErrorUtil,
        private val productRepository : ProductRepository) : SingleUseCase<List<Product>, GetProductDetails.Params>(errorUtil) {

    override fun buildUseCaseObservable(params: GetProductDetails.Params?): Single<List<Product>> {
        return productRepository.getTopSellingProducts()
    }

}