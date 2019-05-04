package com.mmart.sidershopping.view.productlist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ProductListViewModelFactory implements ViewModelProvider.Factory {

    private int categoryId;

    public ProductListViewModelFactory(int categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductListViewModel(categoryId);
    }
}
