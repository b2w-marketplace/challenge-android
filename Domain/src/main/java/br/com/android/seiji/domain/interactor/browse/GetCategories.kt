package br.com.android.seiji.domain.interactor.browse

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.repository.CategoryRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetCategories @Inject constructor(
    private val categoryRepository: CategoryRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Category>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(param: Nothing?): Observable<List<Category>> {
        return categoryRepository.getCategories()
    }
}