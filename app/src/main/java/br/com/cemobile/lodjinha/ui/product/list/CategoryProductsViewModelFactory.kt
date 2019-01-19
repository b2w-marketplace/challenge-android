package br.com.cemobile.lodjinha.ui.product.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.cemobile.data.remote.features.product.ProductRemoteDataSource
import br.com.cemobile.domain.repository.ProductRepository

class CategoryProductsViewModelFactory(
    private val categoryId: Long,
    private val repository: ProductRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(!modelClass.isAssignableFrom(ProductRemoteDataSource::class.java)) {
            CategoryProductsViewModelFactory.UNKNOW_VIEW_MODEL_CLASS
        }
        return CategoryProductsViewModel(categoryId, repository) as T
    }

    private companion object {
        const val UNKNOW_VIEW_MODEL_CLASS = "Unknown ViewModel class"
    }

}