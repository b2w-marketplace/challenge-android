package com.abmm.b2w.alodjinha.activities.main;

import com.abmm.b2w.alodjinha.model.Banner;

import java.util.List;

public interface IMainPresenter {

    void requestBanners();

    List<Banner> getBannersList();
    int getCurrentBannerPosition();
    void setCurrentBannerPosition(int position);
    void deactiveAll();

    void swipeRight();
    void swipeLeft();

    void updateData(Banner banner);

    void resetData(Banner banner);
}
