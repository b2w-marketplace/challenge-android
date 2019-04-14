package br.com.dafle.alodjinha.ui.products

import androidx.lifecycle.MutableLiveData
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.base.BaseViewModel
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.util.disposedBy

class ProductsViewModel(private val productBusinnes: ProductBusinnes,
                        app: LodjinhaApplication): BaseViewModel(app) {

    val items = MutableLiveData<List<Product>>()
    var totalItemsApi = 0
    private var list = mutableListOf<Product>()
    private var offSet = 0

    fun fetchProducts(categoryId: Int, resetList: Boolean = false) {
        if (resetList) {
            this.offSet = 0
            list.clear()
        }
        productBusinnes.all(offSet++, 20, categoryId).subscribe({
            this.totalItemsApi = it.total ?: 0
            progress.value = false
            list.addAll(it.data)
            items.value = list
        },{
            super.handleError(it)
        }).disposedBy(bag)
    }
}