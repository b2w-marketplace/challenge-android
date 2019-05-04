package com.mmart.sidershopping.view.productlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.mmart.sidershopping.model.entity.Product;
import com.mmart.sidershopping.model.paging.ProductDataSourceFactory;

public class ProductListViewModel extends ViewModel {

    LiveData<PageKeyedDataSource<Integer, Product>> liveDataSource;
    LiveData<PagedList<Product>> productPagedList;

    public ProductListViewModel(int categoryId) {

        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(categoryId);

        liveDataSource = productDataSourceFactory.getProductLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20)
                        .build();

        productPagedList = (new LivePagedListBuilder(productDataSourceFactory, config)).build();
    }

}