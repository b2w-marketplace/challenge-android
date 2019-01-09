package br.com.android.seiji.domain.interactor.browse

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProducts @Inject constructor(
    private val productsRepository: ProductsRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Product>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(param: Nothing?): Observable<List<Product>> {
        return productsRepository.getProducts()
    }
}