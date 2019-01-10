package br.com.android.seiji.domain.interactor.product

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProductsByCategoryId @Inject constructor(
        private val productRepository: ProductRepository,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Product>, GetProductsByCategoryId.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Product>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return productRepository.getProductsByCategoryId(params.categoryId, params.offset, params.limit)
    }

    data class Params constructor(val categoryId: Int, val offset: Int, val limit: Int) {
        companion object {
            fun forProduct(categoryId: Int, offset: Int, limit: Int): Params {
                return Params(categoryId, offset, limit)
            }
        }
    }
}