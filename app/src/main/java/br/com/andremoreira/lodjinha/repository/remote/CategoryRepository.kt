package br.com.andremoreira.lodjinha.repository.remote

import br.com.andremoreira.lodjinha.repository.remote.category.CategoryRemoteRep
import br.com.andremoreira.lodjinha.repository.remote.io.CategoriesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryRemoteRep: CategoryRemoteRep
) {

    private val TAG: String = this::class.java.simpleName

    fun getCategory() : Observable<RemoteResponse<CategoriesResponse?>> {

        return categoryRemoteRep.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
//            .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
//            .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}

    }


}