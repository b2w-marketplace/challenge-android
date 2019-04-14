package br.com.dafle.alodjinha.ui.products.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.base.BaseViewModel
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.util.disposedBy

class ProductDetailsViewModel(private val productBusinnes: ProductBusinnes, app: Application): BaseViewModel(app) {

    var product = MutableLiveData<Product>()
    var reserve = MutableLiveData<Pair<String,Boolean>>()

    fun fetch(productId: Int) {
        productBusinnes.get(productId).subscribe({
            progress.value = false
            product.value = it
        },{ handleError(it) }).disposedBy(bag)
    }

    fun reserve(productId: Int) {
        productBusinnes.reserve(productId).subscribe({
            progress.value = false
            reserve.value = Pair(context.getString(R.string.product_details_product_reserved_with_success), true)
        },{
            progress.value = false
            reserve.value = Pair(context.getString(R.string.product_details_error_to_reserve_product), false)
        }).disposedBy(bag)
    }
}