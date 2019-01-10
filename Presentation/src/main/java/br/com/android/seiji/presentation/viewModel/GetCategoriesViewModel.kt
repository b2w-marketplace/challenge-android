package br.com.android.seiji.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.android.seiji.domain.interactor.category.GetCategories
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.presentation.mapper.CategoryViewMapper
import br.com.android.seiji.presentation.model.CategoryView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GetCategoriesViewModel @Inject constructor(
        private val getCategories: GetCategories,
        private val mapper: CategoryViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<CategoryView>>> = MutableLiveData()

    override fun onCleared() {
        getCategories.disposable()
        super.onCleared()
    }

    fun getCategories(): LiveData<Resource<List<CategoryView>>> {
        return liveData
    }

    fun fetchCategories() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getCategories.execute(GetCategoriesSubscriber())
    }

    inner class GetCategoriesSubscriber : DisposableObserver<List<Category>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Category>) {
            liveData.postValue(
                    Resource(
                            ResourceState.SUCCESS,
                            t.map { mapper.mapToView(it) }, null
                    )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}