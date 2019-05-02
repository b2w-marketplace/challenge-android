package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.model.Category;
import com.abmm.b2w.alodjinha.model.Product;
import com.abmm.b2w.alodjinha.utils.Constants.General;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements IMainPresenter {

    private final IMainView mainView;

    private List<Category> categoryList;
    private List<Banner> bannerList;
    private List<Product> productList;
    private int currentBannerPosition;

    private final ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

    MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
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

    @Override
    public void deactiveAll() {
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

    private Callback<Envelope<Banner>> handleBannersCallback() {
        return new Callback<Envelope<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Banner>> call, @NonNull Response<Envelope<Banner>> response) {
                if (response.isSuccessful()) {
                    bannerList = response.body().getData();
                    mainView.initBanners();
                } else {
                    // snackbar error connectivity
                }
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Banner>> call, @NonNull Throwable t) {
                Log.d("TAG", "deu pau!");
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
                    // show snackbar error
                }
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Category>> call, @NonNull Throwable t) {

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
                    // show snackbar
                }
            }

            @Override
            public void onFailure(@NonNull Call<Envelope<Product>> call, @NonNull Throwable t) {
                // show snackbar
            }
        };
    }

    public interface IMainView {
        Context getContext();

        void initBanners();

        void initCategories();

        void initTopSeller();

        void updateData(Banner banner);
    }

}
