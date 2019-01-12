package br.com.android.seiji.domain.interactor.product

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBestSellerProducts @Inject constructor(
        private val productRepository: ProductRepository,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Product>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(param: Nothing?): Observable<List<Product>> {
        return productRepository.getBestSellerProducts()
    }
}