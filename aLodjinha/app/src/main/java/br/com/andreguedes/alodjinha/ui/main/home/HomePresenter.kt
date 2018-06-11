package br.com.andreguedes.alodjinha.ui.main.home

class HomePresenter(
        var view: HomeContract.View
) : HomeContract.Presenter {

    override fun start() {
        getBanners()
        getCategories()
        getProductsBestSellers()
    }

    override fun getBanners() {

    }

    override fun getCategories() {

    }

    override fun getProductsBestSellers() {

    }

}