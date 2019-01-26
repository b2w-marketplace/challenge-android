package com.sumiya.olodjinha.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sumiya.olodjinha.model.CategoryModel

class ProductViewModelFactory(var category: CategoryModel): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(category) as T
    }
}
