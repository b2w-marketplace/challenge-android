package br.com.b2w.lodjinha.features.product.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.features.product.Product

class ProductsFactory(private val api: Api,
                      private val loadingState: MutableLiveData<LoadingState>
) : DataSource.Factory<Int, Product>() {

    var categoryId: Int = -1

    override fun create(): DataSource<Int, Product> = ProductsDataSource(api, categoryId, loadingState)

}