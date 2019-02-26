package br.com.andrecouto.alodjinha.domain.usecase.base

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.response.ErrorResponse
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params>(val errorUtil: DomainErrorUtil) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null) : Single<T>

    open fun execute(compositeDisposable: CompositeDisposable,
            onResponse: (UseCaseResponse<T>) -> Unit,
            params: Params? = null
    ): Disposable {
        val observable = this.buildUseCaseObservable(params)
         val obser = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onResponse(SuccessResponse(it))
                        },
                        {
                            val error = errorUtil.getErrorModel(it)

                            onResponse(ErrorResponse(error))

                        }).also { disposables.add(it) }
        return obser
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }


}