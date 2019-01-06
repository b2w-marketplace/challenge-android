package br.com.android.seiji.domain.interactor.browse

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Categoria
import br.com.android.seiji.domain.repository.CategoriasRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetCategorias @Inject constructor(
    private val categoriasRepository: CategoriasRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Categoria>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(param: Nothing?): Observable<List<Categoria>> {
        return categoriasRepository.getCategorias()
    }
}