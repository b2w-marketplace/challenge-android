package alodjinha.cfgdemelo.com.viewmodel

import alodjinha.cfgdemelo.com.model.BannersResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class HomeViewModel {

    val bannersObservable: PublishSubject<BannersResponse> = PublishSubject.create()

    fun getBanners() {

    }
}