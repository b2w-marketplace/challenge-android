package br.com.android.seiji.domain.interactor.browse

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Produto
import br.com.android.seiji.domain.repository.ProdutosRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProdutos @Inject constructor(
    private val produtosRepository: ProdutosRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Produto>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(param: Nothing?): Observable<List<Produto>> {
        return produtosRepository.getProdutos()
    }
}