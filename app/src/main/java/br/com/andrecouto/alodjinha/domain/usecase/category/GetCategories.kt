package br.com.andrecouto.alodjinha.domain.usecase.category

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.repository.CategoryRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.SingleUseCase
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProductDetails
import io.reactivex.Single
import javax.inject.Inject

class GetCategories @Inject constructor(
        errorUtil: DomainErrorUtil,
        private val categoryRepository: CategoryRepository) : SingleUseCase<List<Category>, GetProductDetails.Params>(errorUtil) {

    override fun buildUseCaseObservable(params: GetProductDetails.Params?): Single<List<Category>> {
        return categoryRepository.getCategories()
    }

}