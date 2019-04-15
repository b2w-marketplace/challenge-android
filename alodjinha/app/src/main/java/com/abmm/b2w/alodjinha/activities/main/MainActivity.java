package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseNavDrawerActivity;
import com.abmm.b2w.alodjinha.adapters.BannerIndicatorAdapter;
import com.abmm.b2w.alodjinha.adapters.CategoryAdapter;
import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.utils.BannerOnSwipeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;

import static com.abmm.b2w.alodjinha.utils.Constants.General.FIRST_POSITION;

public class MainActivity extends BaseNavDrawerActivity implements MainPresenterImpl.IMainView {

    @BindView(R.id.main_banner_img) ImageView mBannerImg;
    @BindView(R.id.main_banner_list) RecyclerView mBannerCarousel;
    @BindView(R.id.main_categories_list) RecyclerView mCategoriesList;

    private IMainPresenter presenter;

    private BannerIndicatorAdapter bannerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupTopbar() {
        toolbar.setTitleTextAppearance(this, R.style.LodjinhaTheme_Toolbar);
        toolbar.setLogo(R.drawable.logo_navbar);
    }

    @Override
    protected void makeRequests() {
        presenter.requestBanners();
        presenter.requestCategories();
    }

    @Override
    public void initBanners() {
        presenter.getBannerList().get(FIRST_POSITION).setActiveON();
        loadBannerImage(presenter.getBannerList().get(FIRST_POSITION).getPictUrl());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false);
        bannerAdapter = new BannerIndicatorAdapter(presenter);

        mBannerCarousel.setLayoutManager(layoutManager);
        mBannerCarousel.setAdapter(bannerAdapter);
    }

    @Override
    public void initCategories() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        CategoryAdapter categoryAdapter = new CategoryAdapter(presenter);
        mCategoriesList.setLayoutManager(layoutManager);
        mCategoriesList.setAdapter(categoryAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void initUi() {
        super.initUi();
        presenter = new MainPresenterImpl(this);
        mBannerImg.setOnTouchListener(getSwipeGesture());
    }

    private BannerOnSwipeListener getSwipeGesture() {
        return new BannerOnSwipeListener(this) {
            @Override
            public void onSwipeRight() { presenter.swipeRight(); }

            @Override
            public void onSwipeLeft() { presenter.swipeLeft(); }
        };
    }

    @Override
    public void updateData(Banner banner) {
        presenter.resetData(banner);

        loadBannerImage(banner.getPictUrl());
        bannerAdapter.notifyDataSetChanged();
        mBannerCarousel.invalidateItemDecorations();
    }

    private void loadBannerImage(String pictUrl) {
        int defaultBannerHeight = mBannerImg.getLayoutParams().height;

        Glide.with(this)
                .load(pictUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(android.R.drawable.ic_dialog_alert)
                )
                .into(mBannerImg);

        mBannerImg.getLayoutParams().height = defaultBannerHeight;
    }
}