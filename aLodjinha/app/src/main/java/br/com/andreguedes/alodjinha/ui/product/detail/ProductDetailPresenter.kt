package br.com.andreguedes.alodjinha.ui.product.detail

import android.util.Log
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailPresenter(
        val view: ProductDetailContract.View,
        private val productId: Int
) : ProductDetailContract.Presenter {

    private val TAG = ProductDetailPresenter::class.java.name

    private val repository = ALodjinhaRepository()

    override fun subscribe() {
        reserveProduct(productId)
    }

    override fun reserveProduct(productId: Int) {
        addDisposable(repository.reserveProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.reservedProduct()
                }, {
                    Log.e(TAG, it.message)
                }))
    }

}