package b2w.com.br.olodjinha.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import b2w.com.br.olodjinha.MainActivity;
import b2w.com.br.olodjinha.injection.NetworkModule;
import b2w.com.br.olodjinha.ui.home.adapters.BannerAdapter;
import b2w.com.br.olodjinha.ui.home.adapters.CategoriesAdapter;
import b2w.com.br.olodjinha.ui.home.adapters.ProductsAdapter;
import b2w.com.br.olodjinha.ui.productdetail.ProductDetailActivity;
import b2w.com.br.olodjinha.ui.queryresult.ResultActivity;
import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.data.models.BannerDTO;
import b2w.com.br.olodjinha.data.models.CategoryDTO;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.injection.DaggerHomeComponent;
import b2w.com.br.olodjinha.injection.HomeComponent;
import b2w.com.br.olodjinha.injection.ScreenFlowModule;
import b2w.com.br.olodjinha.util.UIFeedback.UIFeedback;
import b2w.com.br.olodjinha.util.screenflow.ChangeActivityHandler;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends MvpFragment<HomeContract, HomePresenter> implements HomeContract {

    @Inject
    HomeComponent mInjector;

    @BindView(R.id.viewPager_banner)
    ViewPager mBannerViewPager;

    @BindView(R.id.recyclerview_categories)
    RecyclerView mCategoriesRecyclerView;

    @BindView(R.id.recyclerview_best_sellers)
    RecyclerView mBestSellersRecyclerView;

    @BindView(R.id.tabDots)
    TabLayout mTabDots;

    @BindView(R.id.container)
    View mContainer;

    @Inject
    ChangeActivityHandler mChangeActivityHandler;

    int bannerCurrentPage = 0;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    private ProgressDialog mProgressDialog;

    public static Fragment getInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInjector = DaggerHomeComponent.builder()
                .screenFlowModule(new ScreenFlowModule())
                .networkModule(new NetworkModule())
                .build();
        mInjector.inject(this);
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootview);

        initActionBar();

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Carregando...");
        showProgress(true);

        return rootview;
    }

    private void showProgress(boolean show) {
        if(show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    private void initActionBar() {
        ((MainActivity) getContext()).setAppBar(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().init();
    }

    @Override
    public void showBanners(List<BannerDTO> data) {
        mBannerViewPager.setAdapter(new BannerAdapter(getContext(), data));
        mTabDots.setupWithViewPager(mBannerViewPager, true);
        setPagerAutoScroll(data.size());
    }

    private void setPagerAutoScroll(int numPages){
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (bannerCurrentPage == numPages) {
                bannerCurrentPage = 0;
            }
            mBannerViewPager.setCurrentItem(bannerCurrentPage++, true);
        };

        Timer timer = new Timer();
        timer .schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void showCategories(List<CategoryDTO> data) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        CategoriesAdapter mCategoriesAdapter = new CategoriesAdapter(getContext(), data,
                serializable -> mChangeActivityHandler.startActivityWithExtra(getActivity(), ResultActivity.class, serializable));

        mCategoriesRecyclerView.setLayoutManager(layoutManager);
        mCategoriesRecyclerView.setHasFixedSize(true);
        mCategoriesRecyclerView.setNestedScrollingEnabled(false);
        mCategoriesRecyclerView.setAdapter(mCategoriesAdapter);
    }

    @Override
    public void showBestSellers(List<ProductDTO> data) {
        showProgress(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ProductsAdapter mBestSellersAdapter = new ProductsAdapter(getContext(), data,
                serializable -> mChangeActivityHandler.startActivityWithExtra(getActivity(), ProductDetailActivity.class, serializable));

        mBestSellersRecyclerView.setLayoutManager(layoutManager);
        mBestSellersRecyclerView.setHasFixedSize(true);
        mBestSellersRecyclerView.setNestedScrollingEnabled(false);
        mBestSellersRecyclerView.setAdapter(mBestSellersAdapter);
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return mInjector.presenter();
    }

    @Override
    public void showError() {
        UIFeedback.getAlertDialog(getContext(),
                getString(R.string.error),
                (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).show();
    }
}
