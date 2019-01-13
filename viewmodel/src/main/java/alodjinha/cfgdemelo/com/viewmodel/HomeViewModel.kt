package alodjinha.cfgdemelo.com.viewmodel

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.CategoriesResponse
import alodjinha.cfgdemelo.com.repository.home.HomeRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class HomeViewModel {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val homeRepository by lazy { HomeRepository() }
    val bannersObservable: PublishSubject<BannersResponse> = PublishSubject.create()
    val categoriesObservable: PublishSubject<CategoriesResponse> = PublishSubject.create()
    val errorObservable: PublishSubject<Boolean> = PublishSubject.create()

    fun getBanners()  {
        homeRepository.getBanners().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                bannersObservable.onNext(it)
            }, {
                it.printStackTrace()
                errorObservable.onNext(true)
            }).let { compositeDisposable.add(it) }
    }
    fun getCategories() {
        homeRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                categoriesObservable.onNext(it)
            }, {
                errorObservable.onNext(true)
            }).let { compositeDisposable.add(it) }
    }
}