package br.com.cemobile.lodjinha.ui.product.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.cemobile.domain.features.product.GetBestSellingProducts
import br.com.cemobile.domain.features.product.ReserveProduct

class ProductDetailsViewModelFactory(
    private val useCase: ReserveProduct
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(!modelClass.isAssignableFrom(GetBestSellingProducts::class.java)) {
            ProductDetailsViewModelFactory.UNKNOW_VIEW_MODEL_CLASS
        }
        return ProductDetailsViewModel(useCase) as T
    }

    private companion object {
        const val UNKNOW_VIEW_MODEL_CLASS = "Unknown ViewModel class"
    }
}