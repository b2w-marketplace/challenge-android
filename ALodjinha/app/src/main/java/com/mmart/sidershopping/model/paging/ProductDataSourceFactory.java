package com.mmart.sidershopping.model.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.mmart.sidershopping.model.entity.Product;

public class ProductDataSourceFactory extends DataSource.Factory {

    private final int categoryId;
    private MutableLiveData<PageKeyedDataSource<Integer, Product>> productLiveDataSource = new MutableLiveData<>();

    public ProductDataSourceFactory(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public DataSource create() {
        ProductDataSource productDataSource = new ProductDataSource(categoryId);
        productLiveDataSource.postValue(productDataSource);

        return productDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Product>> getProductLiveDataSource() {
        return productLiveDataSource;
    }

}
