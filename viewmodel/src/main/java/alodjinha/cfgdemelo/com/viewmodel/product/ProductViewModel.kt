package alodjinha.cfgdemelo.com.viewmodel.product

import alodjinha.cfgdemelo.com.model.Product
import alodjinha.cfgdemelo.com.repository.product.ProductRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class ProductViewModel {

    private val productRepository by lazy { ProductRepository() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    val productObservable: PublishSubject<Product> = PublishSubject.create()
    val bookingObservable: PublishSubject<Boolean> = PublishSubject.create()
    val productErrorObservable: PublishSubject<Boolean> = PublishSubject.create()

    fun getProductInfo(id: Int) {
        productRepository.getProduct(id)
            .subscribe({
                productObservable.onNext(it)
            }, {
                productErrorObservable.onNext(true)
                it.printStackTrace()
            }).let { compositeDisposable.add(it) }
    }

    fun bookProduct(id: Int) {
        productRepository.bookProduct(id)
            .subscribe({
                when(it.result) {
                    SUCCESS -> {
                        bookingObservable.onNext(true)
                    }
                    else -> {
                        bookingObservable.onNext(false)
                    }
                }
            }, {
                productErrorObservable.onNext(true)
                it.printStackTrace()
            }).let { compositeDisposable.add(it) }
    }

    companion object {
        private const val SUCCESS = "success"
    }

}