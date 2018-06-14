package com.example.lidjinha.lodjinha.ui.productscategory

import com.example.lidjinha.lodjinha.data.usecase.ProductsUseCaseContract
import com.example.lidjinha.lodjinha.model.Product

class ProductsCategoryPresenter(val view: ProductsCategoryContract.View, private val productUseCase: ProductsUseCaseContract) :
        ProductsCategoryContract.Presenter {

    override fun getProducts(categoryId: Int, isNewValues: Boolean) {
        productUseCase.getProductsCategory(this::onProductsRetrieved, categoryId)
        if (isNewValues) {
            view.showProgressBar()
        } else {
            view.showProgress()
        }
    }

    private fun onProductsRetrieved(products: List<Product>) {
        view.setupComponentsView(products)
        view.hideProgress()
    }

    override fun clearPages() {
        productUseCase.clearProductsCategoryPage()
    }

}