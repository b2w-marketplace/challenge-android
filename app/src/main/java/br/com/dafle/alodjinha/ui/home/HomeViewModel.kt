package br.com.dafle.alodjinha.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import br.com.dafle.alodjinha.base.BaseViewModel
import br.com.dafle.alodjinha.business.HomeBusinnes
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.Banner
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.util.disposedBy
import io.reactivex.rxkotlin.Observables

class HomeViewModel(private val homeBusinnes: HomeBusinnes,
                    private val productBusinnes: ProductBusinnes,
                    app: Application): BaseViewModel(app) {

    val banners = MutableLiveData<List<Banner>>()
    val categories = MutableLiveData<List<Category>>()
    val bestSellers = MutableLiveData<List<Product>>()

    fun fetch() {
        Observables.zip(homeBusinnes.fetchBanner(), homeBusinnes.fetchCategory(), productBusinnes.fetchBestSeller()).subscribe({
            progress.value = false
            banners.value = it.first.data
            categories.value = it.second.data
            bestSellers.value = it.third.data
        }, {
            super.handleError(it)
        }).disposedBy(bag)
    }
}