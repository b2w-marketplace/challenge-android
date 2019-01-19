package br.com.cemobile.lodjinha.ui.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.cemobile.domain.features.banner.GetBanners
import br.com.cemobile.domain.features.category.GetCategories
import br.com.cemobile.domain.features.homedata.GetHomeData
import br.com.cemobile.domain.features.product.GetBestSellingProducts

class HomeViewModelFactory(
    private val useCase: GetHomeData
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(!modelClass.isAssignableFrom(GetBanners::class.java)) {
            UNKNOW_VIEW_MODEL_CLASS
        }
        check(!modelClass.isAssignableFrom(GetCategories::class.java)) {
            UNKNOW_VIEW_MODEL_CLASS
        }
        check(!modelClass.isAssignableFrom(GetBestSellingProducts::class.java)) {
            UNKNOW_VIEW_MODEL_CLASS
        }
        return HomeViewModel(useCase) as T
    }

    private companion object {
        const val UNKNOW_VIEW_MODEL_CLASS = "Unknown ViewModel class"
    }

}