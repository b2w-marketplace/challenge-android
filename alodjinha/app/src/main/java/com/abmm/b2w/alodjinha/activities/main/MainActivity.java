package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseNavDrawerActivity;
import com.abmm.b2w.alodjinha.adapters.BannerIndicatorAdapter;
import com.abmm.b2w.alodjinha.http_module.Envelope;
import com.abmm.b2w.alodjinha.http_module.ILodjinhaApi;
import com.abmm.b2w.alodjinha.http_module.LodjinhaApiClient;
import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.utils.BannerOnSwipeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.abmm.b2w.alodjinha.utils.Constants.General.FIRST_POSITION;

public class MainActivity extends BaseNavDrawerActivity implements IMainContext {

    @BindView(R.id.main_banner_img) ImageView mBannerImg;
    @BindView(R.id.main_banner_list) RecyclerView mBannerCarousel;

    private List<Banner> bannersList;
    private int currentBannerPosition;
    private BannerIndicatorAdapter bannerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void makeRequests() {
        ILodjinhaApi api = LodjinhaApiClient.buildApiClient();

        api.getBanners().enqueue(handleBannersCallback());

    }

    private Callback<Envelope<Banner>> handleBannersCallback() {
        return new Callback<Envelope<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<Envelope<Banner>> call, @NonNull Response<Envelope<Banner>> response) {
                if (response.isSuccessful()) {
                    bannersList = response.body().getData();
                    initializeBanners();
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

    private void initializeBanners() {
        bannersList.get(FIRST_POSITION).setActiveON();
        loadBannerImage(bannersList.get(FIRST_POSITION).getPictUrl());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false);
        bannerAdapter = new BannerIndicatorAdapter(this);

        mBannerCarousel.setLayoutManager(layoutManager);
        mBannerCarousel.setAdapter(bannerAdapter);
    }

    @Override
    public void setupTopbar() {
        toolbar.setTitleTextAppearance(this, R.style.LodjinhaTheme_Toolbar);
        toolbar.setLogo(R.drawable.logo_navbar);
    }

    @Override
    protected void initUi() {
        super.initUi();
        mBannerImg.setOnTouchListener(getSwipeGesture());
    }

    private BannerOnSwipeListener getSwipeGesture() {
        return new BannerOnSwipeListener(this) {
            @Override
            public void onSwipeRight() {
                currentBannerPosition = (currentBannerPosition > FIRST_POSITION ? currentBannerPosition : bannersList.size()) - 1;
                updateData(bannersList.get(currentBannerPosition));
            }

            @Override
            public void onSwipeLeft() {
                currentBannerPosition = (currentBannerPosition < bannersList.size() - 1 ? currentBannerPosition + 1 : FIRST_POSITION);
                updateData(bannersList.get(currentBannerPosition));
            }
        };
    }

    /* Context */
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public List<Banner> getBanners() {
        return bannersList;
    }

    @Override
    public int getCurrentPosition() {
        return this.currentBannerPosition;
    }

    @Override
    public void setCurrentPosition(int position) {
        this.currentBannerPosition = position;
    }

    @Override
    public void updateData(Banner banner) {
        deactiveAll();
        currentBannerPosition = banner.getPosition();
        banner.setActiveON();
        loadBannerImage(banner.getPictUrl());
        bannerAdapter.notifyDataSetChanged();
        mBannerCarousel.invalidateItemDecorations();
    }

    public void deactiveAll() {
        for (int i = 0; i < bannersList.size(); i++) {
            bannersList.get(i).setActiveOFF();
            bannersList.get(i).setPosition(i);
        }
    }

    private void loadBannerImage(String pictUrl) {

        Glide.with(this)
                .load(pictUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(android.R.drawable.ic_dialog_alert)
                )
                .into(mBannerImg);

        ConstraintLayout.LayoutParams imageViewParams = new ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        mBannerImg.setLayoutParams(imageViewParams);

    }
}