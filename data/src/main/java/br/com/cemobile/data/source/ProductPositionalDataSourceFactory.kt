package br.com.cemobile.data.source

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.repository.ProductRepository

class ProductPositionalDataSourceFactory(
    private val repository: ProductRepository,
    private val categoryId: Long
) : DataSource.Factory<Int, Product>() {

    private val productDataSourceLiveData = MutableLiveData<ProductPositionalDataSource>()

    override fun create(): DataSource<Int, Product> =
        ProductPositionalDataSource(repository, categoryId)

    fun getProductDataSourceLiveData() = productDataSourceLiveData

}