package com.sumiya.olodjinha.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sumiya.olodjinha.Model.CategoryModel

class ProductViewModelFactory(var category: CategoryModel): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(category) as T
    }
}
