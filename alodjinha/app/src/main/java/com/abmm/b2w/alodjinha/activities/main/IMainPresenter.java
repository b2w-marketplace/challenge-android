package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;

import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;

import java.util.List;

public interface IMainPresenter {

    Context getContext();
    void requestBanners();
    void requestCategories();
    void requestTopSellers();

    List<Banner> getBannerList();
    List<Category> getCategoryList();
    List<Product> getProductList();

    int getCurrentBannerPosition();
    void setCurrentBannerPosition(int position);
    void deactiveAll();

    void swipeRight();
    void swipeLeft();

    void resetData(Banner banner);
    void updateData(Banner banner);
}
