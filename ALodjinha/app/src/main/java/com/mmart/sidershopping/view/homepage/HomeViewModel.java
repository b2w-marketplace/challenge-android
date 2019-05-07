package com.mmart.sidershopping.view.homepage;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mmart.sidershopping.model.entity.Banner;
import com.mmart.sidershopping.model.entity.Category;
import com.mmart.sidershopping.model.entity.Product;

import java.util.List;

public class HomeViewModel extends ViewModel {


    public MutableLiveData<List<Banner>> getBanners() {
        HomeRepository homeRepository = new HomeRepository();
        return homeRepository.getBanner();
    }

    public MutableLiveData<List<Category>> getCategories() {
        HomeRepository homeRepository = new HomeRepository();
        return homeRepository.getCategories();
    }

    public MutableLiveData<List<Product>> getBestSellers() {
        HomeRepository homeRepository = new HomeRepository();
        return homeRepository.getBestSellers();
    }
}
