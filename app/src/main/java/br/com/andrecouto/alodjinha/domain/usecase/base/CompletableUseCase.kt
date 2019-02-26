package br.com.andrecouto.alodjinha.domain.usecase.base

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.response.*
import io.reactivex.Completable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params>(val errorUtil: DomainErrorUtil)  {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null) : Completable

    open fun execute(compositeDisposable: CompositeDisposable,
                     onResponse: (UseCaseResponse<String>) -> Unit,
                     params: Params? = null
    ): Disposable {
        val observable = this.buildUseCaseObservable(params)
        val obser = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            onResponse(SuccessResponse("OK"))
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