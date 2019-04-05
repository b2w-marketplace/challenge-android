package br.com.andremoreira.lodjinha.repository

import br.com.andremoreira.lodjinha.repository.entity.Product
import br.com.andremoreira.lodjinha.repository.remote.RemoteResponse
import br.com.andremoreira.lodjinha.repository.remote.io.ProductReservationResponse
import br.com.andremoreira.lodjinha.repository.remote.io.ProductResponse
import br.com.andremoreira.lodjinha.repository.remote.product.ProductRemoteRep
import br.com.andremoreira.lodjinha.repository.remote.validateHttpCode
import br.com.andremoreira.lodjinha.utils.LoadingDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productRemoteRep: ProductRemoteRep,
    private val loadingDialog: LoadingDialog
    ) {

    private val TAG: String = this::class.java.simpleName

    fun getProductsMoreSallers() : Observable<RemoteResponse<ProductResponse?>> {

        return productRemoteRep.getProductsMoreSallers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
    }

    fun getProductsByCategory(offset: Int, limite: Int,categoriaId: Int) : Observable<RemoteResponse<ProductResponse?>> {

        return productRemoteRep.getProductsByCategory(offset, limite, categoriaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
            .doOnSubscribe {loadingDialog.showLoading() }
            .doOnTerminate{loadingDialog.dismissLoading()}
    }

    fun getProductDetail(produtoId: Int) : Observable<RemoteResponse<Product?>> {

        return productRemoteRep.getProductDetail(produtoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
            .doOnSubscribe {loadingDialog.showLoading() }
            .doOnTerminate{loadingDialog.dismissLoading()}
    }

    fun productReservation(produtoId: Int) : Observable<RemoteResponse<ProductReservationResponse?>> {

        return productRemoteRep.productReservation(produtoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
    }


}