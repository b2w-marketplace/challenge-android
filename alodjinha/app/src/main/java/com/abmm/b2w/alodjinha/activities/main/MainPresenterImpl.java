package com.abmm.b2w.alodjinha.activities.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abmm.b2w.alodjinha.utils.Constants.General.FIRST_POSITION;

public class MainPresenterImpl implements IMainPresenter {

    private IMainView mainView;

    private List<Banner> bannersList;
    private int currentBannerPosition;

    MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void requestBanners() {
        ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

        api.getBanners().enqueue(handleBannersCallback());
    }

    @Override
    public List<Banner> getBannersList() {
        return this.bannersList;
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
        for (int i = 0; i < bannersList.size(); i++) {
            bannersList.get(i).setActiveOFF();
            bannersList.get(i).setPosition(i);
        }
    }

    @Override
    public void swipeRight() {
        currentBannerPosition = (currentBannerPosition > FIRST_POSITION ? currentBannerPosition : bannersList.size()) - 1;
        updateData(bannersList.get(currentBannerPosition));
    }

    @Override
    public void swipeLeft() {
        currentBannerPosition = (currentBannerPosition < bannersList.size() - 1 ? currentBannerPosition + 1 : FIRST_POSITION);
        mainView.updateData(bannersList.get(currentBannerPosition));
    }

    @Override
    public void updateData(Banner banner) {
        mainView.updateData(banner);
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
                    bannersList = response.body().getData();
                    mainView.initializeBanners();
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

    public interface IMainView {
        void initializeBanners();
        void updateData(Banner banner);
    }

}
