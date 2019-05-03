package com.abmm.b2w.alodjinha.activities.main;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abmm.b2w.alodjinha.R;
import com.abmm.b2w.alodjinha.activities.BaseNavDrawerActivity;
import com.abmm.b2w.alodjinha.adapters.BannerIndicatorAdapter;
import com.abmm.b2w.alodjinha.adapters.CategoryAdapter;
import com.abmm.b2w.alodjinha.adapters.TopSellerAdapter;
import com.abmm.b2w.alodjinha.model.Banner;
import com.abmm.b2w.alodjinha.utils.BannerOnSwipeListener;
import com.abmm.b2w.alodjinha.utils.Constants.General;
import com.abmm.b2w.alodjinha.utils.DividerItemDecoration;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseNavDrawerActivity implements MainPresenterImpl.IMainView {

    @BindView(R.id.main_banner_img) ImageView mBannerImg;
    @BindView(R.id.main_banner_list) RecyclerView mBannerCarousel;
    @BindView(R.id.main_categories_list) RecyclerView mCategoriesRecyclerView;
    @BindView(R.id.main_topseller_list) RecyclerView mTopSellerRecyclerView;
    @BindView(R.id.main_categories_title) TextView mCategoriesTxt;
    @BindView(R.id.main_topseller_title) TextView mTopSellerTxt;
    @BindView(R.id.main_banner_carview) CardView mBannerFrame;

    private IMainPresenter presenter;
    private BannerIndicatorAdapter mBannerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUi() {
        super.initUi();
        presenter = new MainPresenterImpl(this);
        mBannerImg.setOnTouchListener(getSwipeGesture());

        mCategoriesTxt.setVisibility(View.INVISIBLE);
        mTopSellerTxt.setVisibility(View.INVISIBLE);
        mBannerFrame.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setupTopbar() {
        toolbar.setTitleTextAppearance(this, R.style.LodjinhaTheme_Toolbar);
        toolbar.setLogo(R.drawable.logo_navbar);
    }

    @Override
    protected void makeRequests() {
        blockUi();
        presenter.requestBanners();
        presenter.requestCategories();
        presenter.requestTopSellers();
    }

    @Override
    public void initBanners() {
        mBannerFrame.setVisibility(View.VISIBLE);
        presenter.getBannerList().get(General.FIRST_POSITION).setActiveON();
        loadBannerImage(presenter.getBannerList().get(General.FIRST_POSITION).getPictUrl());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false);
        mBannerAdapter = new BannerIndicatorAdapter(presenter);

        mBannerCarousel.setLayoutManager(layoutManager);
        mBannerCarousel.setAdapter(mBannerAdapter);
        startBannerTimerCarousel();
    }

    @Override
    public void initCategories() {
        mCategoriesTxt.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        CategoryAdapter adapter = new CategoryAdapter(presenter);
        mCategoriesRecyclerView.setLayoutManager(layoutManager);
        mCategoriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initTopSeller() {
        mTopSellerTxt.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TopSellerAdapter adapter = new TopSellerAdapter(presenter);
        mTopSellerRecyclerView.setLayoutManager(layoutManager);
        mTopSellerRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mTopSellerRecyclerView.setAdapter(adapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateData(Banner banner) {
        presenter.resetData(banner);

        loadBannerImage(banner.getPictUrl());
        mBannerAdapter.notifyDataSetChanged();
        mBannerCarousel.invalidateItemDecorations();
    }

    @Override
    public void showError(int code) {
        Toast.makeText(this, "code="+ code, Toast.LENGTH_SHORT).show();
    }

    private BannerOnSwipeListener getSwipeGesture() {
        return new BannerOnSwipeListener(this) {
            @Override
            public void onSwipeRight() { presenter.swipeRight(); }

            @Override
            public void onSwipeLeft() { presenter.swipeLeft(); }
        };
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

    private void startBannerTimerCarousel() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.swipeLeft();
                    }
                });
            }
        }, General.BANNER_DURATION_TIMER, General.BANNER_DURATION_TIMER);
    }
}