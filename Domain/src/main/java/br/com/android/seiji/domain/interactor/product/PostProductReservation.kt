package br.com.android.seiji.domain.interactor.product

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.CompletableUseCase
import br.com.android.seiji.domain.repository.ProductRepository
import io.reactivex.Completable
import javax.inject.Inject

open class PostProductReservation @Inject constructor(
    private val productRepository: ProductRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<PostProductReservation.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return productRepository.doProductReservation(params.productId)
    }

    data class Params constructor(val productId: Int) {
        companion object {
            fun forProduct(productId: Int): Params {
                return Params(productId)
            }
        }
    }
}