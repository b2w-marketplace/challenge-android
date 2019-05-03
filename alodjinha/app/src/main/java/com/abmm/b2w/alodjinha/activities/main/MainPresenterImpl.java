package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.General;
import com.abmm.b2w.alodjinha.activities.IBasePresenterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements IMainPresenter {

    private final IMainView mainView;

    private List<Banner> bannerList;
    private List<Category> categoryList;
    private List<Product> productList;
    private int currentBannerPosition;

    private boolean bannersLoaded = false;
    private boolean categoriesLoaded = false;
    private boolean topSellersLoaded = false;

    private final ILodjinhaApi api;

    MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
        this.api = LodjinhaApiClient.buildApiClient();
        this.bannerList = new ArrayList<>();
        this.categoryList = new ArrayList<>();
        this.productList = new ArrayList<>();
    }

    @Override
    public Context getContext() {
        return mainView.getContext();
    }

    @Override
    public void requestBanners() {
        api.getBanners().enqueue(handleBannersCallback());
    }

    @Override
    public void requestCategories() {
        api.getCategories().enqueue(handleCategoriesCallback());
    }

    @Override
    public void requestTopSellers() {
        api.getTopSeller().enqueue(handleTopSellersCallback());
    }

    @Override
    public int getCurrentBannerPosition() {
        return currentBannerPosition;
    }

    @Override
    public void setCurrentBannerPosition(int position) {
        this.currentBannerPosition = position;
    }

    private void deactiveAll() {
        for (int i = 0; i < bannerList.size(); i++) {
            bannerList.get(i).setActiveOFF();
            bannerList.get(i).setPosition(i);
        }
    }

    @Override
    public void swipeRight() {
        currentBannerPosition = (currentBannerPosition > General.FIRST_POSITION
                ? currentBannerPosition
                : bannerList.size()) - 1;
        updateData(bannerList.get(currentBannerPosition));
    }

    @Override
    public void swipeLeft() {
        currentBannerPosition = (currentBannerPosition < bannerList.size() - 1
                ? currentBannerPosition + 1
                : General.FIRST_POSITION);
        mainView.updateData(bannerList.get(currentBannerPosition));
    }

    @Override
    public void updateData(Banner banner) {
        mainView.updateData(banner);
    }

    @Override
    public List<Banner> getBannerList() {
        return this.bannerList;
    }

    @Override
    public List<Category> getCategoryList() {
        return this.categoryList;
    }

    @Override
    public List<Product> getTopSellersList() {
        return this.productList;
    }

    @Override
    public void resetData(Banner banner) {
        deactiveAll();
        currentBannerPosition = banner.getPosition();
        banner.setActiveON();
    }

    private void hideLoading() {
        if (bannersLoaded && categoriesLoaded && topSellersLoaded) {
            mainView.releaseUi();
            if (bannerList.isEmpty() || categoryList.isEmpty() || productList.isEmpty()) {
                mainView.showError();
            }
        }
    }

    private Callback<Envelope<Banner>> handleBannersCallback() {
        return new Callback<Envelope<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Banner>> call, @NonNull Response<Envelope<Banner>> response) {
                if (response.isSuccessful()) {
                    bannerList = response.body().getData();
                    mainView.initBanners();
                } else {
                    mainView.showError(response.code());
                }
                bannersLoaded = true;
                hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Banner>> call, @NonNull Throwable t) {
                mainView.showError();
                bannersLoaded = true;
                hideLoading();
            }
        };
    }

    private Callback<Envelope<Category>> handleCategoriesCallback() {
        return new Callback<Envelope<Category>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Category>> call, @NonNull Response<Envelope<Category>> response) {
                if (response.isSuccessful()) {
                    categoryList = response.body().getData();
                    mainView.initCategories();
                } else {
                    mainView.showError(response.code());
                }
                categoriesLoaded = true;
                hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Category>> call, @NonNull Throwable t) {
                mainView.showError();
                categoriesLoaded = true;
                hideLoading();
            }
        };
    }

    private Callback<Envelope<Product>> handleTopSellersCallback() {
        return new Callback<Envelope<Product>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Product>> call, @NonNull Response<Envelope<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body().getData();
                    mainView.initTopSeller();
                } else {
                    mainView.showError(response.code());
                }
                topSellersLoaded = true;
                hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Product>> call, @NonNull Throwable t) {
                mainView.showError();
                topSellersLoaded = true;
                hideLoading();
            }
        };
    }

    public interface IMainView extends IBasePresenterView {
        Context getContext();

        void initBanners();

        void initCategories();

        void initTopSeller();

        void updateData(Banner banner);
    }
}
