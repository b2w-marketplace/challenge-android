package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;

import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;

import java.util.List;

public interface IMainPresenter {

    Context getContext();
    void requestBanners();
    void requestCategories();

    List<Banner> getBannerList();
    int getCurrentBannerPosition();
    void setCurrentBannerPosition(int position);
    void deactiveAll();

    void swipeRight();
    void swipeLeft();

    void resetData(Banner banner);
    void updateData(Banner banner);

    List<Category> getCategoryList();

}
