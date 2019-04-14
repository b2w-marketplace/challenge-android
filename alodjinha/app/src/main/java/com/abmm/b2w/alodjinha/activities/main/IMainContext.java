package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;

import com.abmm.b2w.alodjinha.model.Banner;

import java.util.List;

public interface IMainContext {

    Context getContext();
    List<Banner> getBanners();

    int getCurrentPosition();

    void setCurrentPosition(int layoutPosition);

    void updateData(Banner banner);
}
