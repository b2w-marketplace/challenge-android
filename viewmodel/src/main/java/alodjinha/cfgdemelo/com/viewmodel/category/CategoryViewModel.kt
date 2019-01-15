package alodjinha.cfgdemelo.com.viewmodel.category

import alodjinha.cfgdemelo.com.model.ProductsResponse
import alodjinha.cfgdemelo.com.repository.category.CategoryRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class CategoryViewModel {

    private val categoryRepository by lazy { CategoryRepository() }
    private val compositeDisposable by lazy { CompositeDisposable() }
    private var offset = 1
    private val limit = 22

    val productsObservable: PublishSubject<ProductsResponse> = PublishSubject.create()

    fun getProductsByCategoryId(id: Int) {
        categoryRepository.getProductsByCategoryId(offset, limit, id)
            .subscribe({
                productsObservable.onNext(it)
                ++offset
            }, {
                it.printStackTrace()
            }).let { compositeDisposable.add(it) }
    }

}