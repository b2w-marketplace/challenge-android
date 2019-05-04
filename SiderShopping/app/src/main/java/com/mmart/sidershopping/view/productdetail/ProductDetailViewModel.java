package com.mmart.sidershopping.view.productdetail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mmart.sidershopping.model.entity.Product;

public class ProductDetailViewModel extends ViewModel {

    public MutableLiveData<Product> getProduct(int productId) {

        ProductDetailRepository productDetailRepository = new ProductDetailRepository();
        return productDetailRepository.getProduct(productId);
    }

    public MutableLiveData<String> reserve(int productId) {

        ProductDetailRepository productDetailRepository = new ProductDetailRepository();
        return productDetailRepository.reserve(productId);
    }
}
