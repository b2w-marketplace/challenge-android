package br.com.andreguedes.alodjinha.ui.category_products

import android.util.Log
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryProductsPresenter(
        val view: CategoryProductsContract.View,
        private val categoryId: Int
) : CategoryProductsContract.Presenter {

    private val TAG = CategoryProductsPresenter::class.java.name

    private val repository = ALodjinhaRepository()

    override fun subscribe() {}

    override fun getProductsFromCategory(offset: Int) {
        addDisposable(repository.getProducts(offset, BuildConfig.LIMIT_OFFSET, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProducts(it.productList!!)
                }, {
                    Log.e(TAG, it.message)
                }))
    }

}